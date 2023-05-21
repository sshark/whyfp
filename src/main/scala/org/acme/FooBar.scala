package org.acme

import cats.MonadError
import cats.effect.std.Console
import cats.effect.{IO, IOApp}
import cats.syntax.all._

import scala.util.Random

/** FP with no side effects
  */
object FooBar extends IOApp.Simple {
  case class BetterLuck() extends Exception("Better luck next time")

  val MAX_NUM   = 10
  val MAX_GUESS = 3

  override def run: IO[Unit] = for {
    numPicked <- IO(Random.nextInt(MAX_NUM) + 1)
    _         <- IO.println(s"Psst... FooBar, my guess is $numPicked")
    status    <- guess[IO](MAX_GUESS, numPicked).attempt
    _ <- status match {
      case Left(ex) => IO.println(ex.getMessage)
      case Right(s)                => IO.println(s"You took $s guess(es)")
    }
  } yield ()

  private type MonadThrowable[F[_]] = MonadError[F, Throwable]

  def guess[F[_]: MonadThrowable: Console](maxGuess: Int, numPicked: Int): F[Int] = {
    def guessLocal(guesses: Int): F[Int] =
      if (guesses < maxGuess)
        for {
          guessAttempt <- Console[F]
            .print("Please enter a number: ") *> Console[F].readLine.map(_.toInt).attempt
          response <- guessAttempt match {
            case Right(n) =>
              if (n == numPicked)
                Console[F]
                  .println(s"$numPicked is correct. You guess right") *> MonadError[F, Throwable]
                  .pure(guesses + 1)
              else if (guesses + 1 != maxGuess)
                Console[F].println("Try again.") *> guessLocal(guesses + 1)
              else
                guessLocal(guesses + 1)
            case Left(_: NumberFormatException) =>
              Console[F].println(s"Please use numbers only") *> guessLocal(guesses)
            case Left(ex) => MonadError[F, Throwable].raiseError(ex)
          }
        } yield response
      else MonadError[F, Throwable].raiseError(BetterLuck())

    guessLocal(0)
  }
}
