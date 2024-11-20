package org.acme

import scala.io.StdIn.readLine
import scala.util.Random
import scala.util.control.Breaks.*

/** Java++ code.
  */
@SuppressWarnings(Array("org.wartremover.warts.While", "org.wartremover.warts.Var", "org.wartremover.warts.Throw"))
final case class Foo(maxGuess: Int, numPicked: Int):
  def run(): Unit =
    var guesses = 1     // side-effect
    var guessed = false // side-effect

    while guesses <= maxGuess && !guessed do
      var myGuess = 0
      try myGuess = readLine("Please enter a number: ").toInt
      catch
        case _: NumberFormatException => println("Please use numbers only")
        case ex: Exception            => throw ex

      if myGuess == numPicked then
        println(s"$numPicked is correct. You guess right")
        guessed = true
      else
        guesses += 1
        if guesses != maxGuess + 1 then println("Try again.")

    if guessed then println(s"You took $guesses guess(es)")
    else println("Better luck next time")

object Foo:
  @main
  def mainFoo(): Unit =
    val MAX_NUM   = 10
    val MAX_GUESS = 3

    val numPicked = Random.nextInt(MAX_NUM) + 1
    println(s"Psst... Foo, my guess is $numPicked")

    new Foo(MAX_GUESS, numPicked).run()
