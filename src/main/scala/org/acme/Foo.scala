package org.acme

import scala.io.StdIn.readLine
import scala.util.Random
import scala.util.control.Breaks._

/** Java++ code.
  */
case class Foo(maxGuess: Int, numPicked: Int) {
  def run(): Unit = {
    var guesses = 1     // side-effect
    var guessed = false // side-effect

    breakable { // Java break substitution
      while (guesses <= maxGuess)
        try {
          val myGuess = readLine("Please enter a number: ").toInt

          if (myGuess == numPicked) {
            println(s"$numPicked is correct. You guess right")
            guessed = true
            break() // Java break substitution
          }

          guesses += 1
          if (guesses != maxGuess + 1) println("Try again.")
        } catch {
          case _: NumberFormatException => println("Please use numbers only")
          case ex: Exception            => throw ex
        }
    }
    if (guessed) println(s"You took $guesses guess(es)")
    else println("Better luck next time")
  }
}

object Foo extends App {
  val MAX_NUM   = 10
  val MAX_GUESS = 3

  val numPicked = Random.nextInt(MAX_NUM) + 1
  println(s"Psst... Foo, my guess is $numPicked")

  new Foo(MAX_GUESS, numPicked).run()
}
