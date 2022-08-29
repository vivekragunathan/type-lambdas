## Playing with Scala type lambdas

### Question

[**`@morgen-peschke`**](https://github.com/morgen-peschke) asked:

If you have this definition:

```scala
trait OptionLike[F[_]] {
  def get[A](a: F[A]): Option[A]
}

def sumWithin[F[_]](elements: List[F[Int]])(implicit OL: OptionLike[F]): Int =
  elements.flatMap(OL.get(_)).sum
```

How do you write an instance that will enable this to compile and run?

```scala
val input1: List[Either[String, Int]] = 
  List(Right(5), Left("a"), Right(4), Left("b"), Right(3), Right(2), Left("c"), Right(1))
  
val input2: List[Either[Char, Int]] =
    List(Left('a'), Right(4), Left('b'), Right(3), Right(2), Left('c'), Right(1))
  
assert(sumWithin(input1) == 15 && sumWithin(input2) == 10)
```

### Info

This repo contains my solution (without using type lambdas because I wasn't aware of their support in Scala 2), and also [**`@morgen-peschke`**](https://github.com/morgen-peschke)'s solution using type lambdas.

FYI, Scala 3 has nicer syntax for type lambdas

```scala
[X] =>> List[X]
[T, E] =>> Either[Option[T], E]
[F[_]] =>> F[Int]
```

There is a great blog [**post**](https://blog.rockthejvm.com/scala-3-type-lambdas/) about it on [Rock the JVM](https://blog.rockthejvm.com).
