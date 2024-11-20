package org.acme

import cats.MonadThrow
import cats.effect.std.Console
import cats.effect.{IO, IOApp}
import cats.syntax.all.*

import scala.util.Random

/** FP with no side effects
  */
object FooBar extends IOApp.Simple:
  case class BetterLuck() extends Exception("Better luck next time")

  val MAX_NUM   = 10
  val MAX_GUESS = 3

  override def run: IO[Unit] =
    for
      numPicked <- IO(Random.nextInt(MAX_NUM) + 1)
      _         <- IO.println(s"Psst... FooBar, my guess is $numPicked")
      status    <- guess[IO](MAX_GUESS, numPicked).attempt
      _ <- status match
        case Left(ex) => IO.println(ex.getMessage)
        case Right(s) => IO.println(s"You took $s guess(es)")
    yield ()

  def guess[F[_]: MonadThrow: Console](maxGuess: Int, numPicked: Int): F[Int] =
    @SuppressWarnings(Array("org.wartremover.warts.Recursion"))
    def _guess(guesses: Int): F[Int] =
      if guesses < maxGuess then
        for
          attempts <- Console[F]
            .print("Please enter a number: ") *> Console[F].readLine.map(_.toInt).attempt
          response <- attempts match
            case Right(n) if n == numPicked =>
              Console[F]
                .println(s"$numPicked is correct. You guess right") *> MonadThrow[F]
                .pure(guesses + 1)
            case Right(_) if guesses + 1 != maxGuess =>
              Console[F].println("Try again.") *> _guess(guesses + 1)
            case Right(_) => _guess(guesses + 1)
            case Left(_: NumberFormatException) =>
              Console[F].println(s"Please use numbers only") *> _guess(guesses)
            case Left(ex) => MonadThrow[F].raiseError(ex)
        yield response
      else MonadThrow[F].raiseError(BetterLuck())

    _guess(0)
