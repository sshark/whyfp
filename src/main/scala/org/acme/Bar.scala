package org.acme

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.{Random, Try}

/** Typical Scala with no variable used and no exception thrown. However,
  * there are side effects like println and random functions in the code
  */
object Bar extends App {
  case class BetterLuck() extends Exception("Better luck next time")

  def guess(maxGuess: Int, numPicked: Int): Either[Throwable, Int] = {

    // Sacrifice tail recursion, @tailrec, to catch parse exception
    def guessLocal(guesses: Int): Either[Throwable, Int] =
      if (guesses < maxGuess) {
        Try(readLine("Please enter a number: ").toInt).toEither.fold(
          {
            case _: NumberFormatException =>
              println("Please use numbers only")
              guessLocal(guesses)
            case t: Exception => Left(t)
          },
          myGuess =>
            if (myGuess == numPicked) {
              println(s"$numPicked is correct. You guess right")
              Right[Throwable, Int](guesses + 1)
            } else if (guesses + 1 != maxGuess) {
              println("Try again.")
              guessLocal(guesses + 1)
            } else
              guessLocal(guesses + 1)
        )
      } else {
        Left[Throwable, Int](BetterLuck())
      }

    guessLocal(0)
  }

  val MAX_NUM = 10
  val MAX_GUESS = 3
  val numPicked = Random.nextInt(MAX_NUM) + 1
  println(s"Psst... Bar, my guess is $numPicked")

  guess(MAX_GUESS, numPicked) match {
    case Left(s)  => println(s)
    case Right(s) => println(s"You took $s guess(es)")
  }
}
