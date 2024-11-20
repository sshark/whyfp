package org.acme

import cats.implicits.*

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.{Failure, Random, Success, Try}

/* Typical Scala application does not use variable, only values are used and, no exception thrown. However,
 * there are side effects functions like println and random littered in the code
 */
object Bar:
  private case class BetterLuck() extends Exception("Better luck next time")
  private case class TryAgain()   extends Exception("Try again")

  def guess(maxGuess: Int, numPicked: Int): Try[(Int, Int)] =
    retry(maxGuess, BetterLuck()) { attempts =>
      readAsInt() match
        case r @ Success(num) if num == numPicked =>
          println(s"$numPicked is correct. You guess right")
          r
        case r @ Success(_) if attempts > 1 =>
          println(TryAgain().getMessage)
          r
        case _ => Failure[Int](BetterLuck())
    }

  @tailrec
  private def readAsInt(): Try[Int] = Try(readLine("Please enter a number: ").toInt) match
    case Failure(t: NumberFormatException) =>
      println("Please use numbers only")
      readAsInt()
    case result => result

  @main
  def mainBar(): Unit =
    val MAX_NUM   = 10
    val MAX_GUESS = 3
    val numPicked = Random.nextInt(MAX_NUM) + 1
    println(s"Psst... Bar, my guess is $numPicked")

    guess(MAX_GUESS, numPicked) match
      case Failure(t)             => println(t.getMessage)
      case Success((_, attempts)) => println(s"You took ${MAX_GUESS - attempts + 1} guess(es)")
