import scala.language.higherKinds

trait OptionLike[F[_]] {
  def get[A](a: F[A]): Option[A]
}

object OptionLike {
  def sumWithin[F[_]](elements: List[F[Int]])(implicit OL: OptionLike[F]): Int =
    elements.flatMap(OL.get(_)).sum
}
