# Moving to FP

This project shows the transformation of an imperative code to a FP(Functional Programming) code using a number guessing game.  

The game picks a random number between 1 and an arbitrary number for the player to guess. The player has to guess the chosen number before the player runs out retries. 

## The Flavors Are in The Source
### Foo.scala (Imperative)

`Foo.scala` is written using Java coding convention in general and act as the base point for this project. The code uses impure, side-effecting, functions and, variables instead of values. In short, it gets the job done. Period.

### Bar.scala (almost FP)

`Bar.scala` uses many Scala features and fluent style. However, side effects, 
`Console.println(...)` and `Random.nextInt(...)`, are seen in the code.

### FooBar.scala (FP)

`FooBar.scala` is written in pure functions using the Typelevel [Cats Effect](https://typelevel.org/cats-effect/) library. I feel more confident 
knowing that error routes and nulls are taken care of seamlessly.

## Conclusion

Does learning the more complicated FP help to improve the software maintenance?