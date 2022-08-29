import OptionLike._

import scala.annotation.nowarn
import scala.reflect.runtime.universe._

/**
 * Using type lambdas
 */
object Solution2 extends App {

  type TL[L] = { type E[R] = Either[L, R] } // Type lambda aliased to TL[L]

  implicit def optionLikeInstance[L]: OptionLike[TL[L]#E] =
    new OptionLike[TL[L]#E] {
      def get[R](a: Either[L, R]): Option[R] = a.toOption
    }

  def testSingleEither[L, R](input: Either[L, R]): Unit = {
    @nowarn val ddd: TL[L]#E[R] = implicitly[TL[L]#E[R]](input)
    val result:      Option[R]  = implicitly[OptionLike[TL[L]#E]].get(input)

    input match {
      case Right(_)    => println(result)
      case Left(value) => println(s"Left($value) ==> $result")
    }
  }

  def testListOfEither[L](left: L)(implicit
    OL:                         OptionLike[TL[L]#E],
    W:                          WeakTypeTag[L]
  ): Unit = {
    val input  = Data.gen(left)
    val result = sumWithin[TL[L]#E](input)
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
