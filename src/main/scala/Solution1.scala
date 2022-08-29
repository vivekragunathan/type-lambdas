import OptionLike._

import scala.annotation.nowarn
import scala.reflect.runtime.universe._

object Solution1 extends App {
  trait MonoEither[L] {
    type E[R] = Either[L, R]
  }

  implicit def optionLikeInstance[L]: OptionLike[MonoEither[L]#E] =
    new OptionLike[MonoEither[L]#E] {
      override def get[A](a: MonoEither[L]#E[A]): Option[A] = a.toOption
    }

  def testSingleEither[L, R](input: Either[L, R]): Unit = {
    @nowarn val ddd: MonoEither[L]#E[R] = implicitly[MonoEither[L]#E[R]](input)
    val result:      Option[R]          = implicitly[OptionLike[MonoEither[L]#E]].get(input)

    input match {
      case Right(_)    => println(result)
      case Left(value) => println(s"Left($value) ==> $result")
    }
  }

  def testListOfEither[L](left: L)(implicit
    OL:                         OptionLike[MonoEither[L]#E],
    W:                          WeakTypeTag[L]
  ): Unit = {
    val input  = Data.gen(left)
    val result = sumWithin[MonoEither[L]#E](input)
    println(s"sumWithin( ${W.tpe} ): $result")
    assert(result == 15)
  }

  testSingleEither(Right(22))
  testSingleEither(Right(23))
  testSingleEither(Right(24))
  testSingleEither(Left("Hello"))

  testListOfEither('a')
  testListOfEither("HELLO")
  testListOfEither((5, 'a'))
}
