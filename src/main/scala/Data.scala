object Data {
  def gen[L](left: L): List[Either[L, Int]] =
    List(
      Right(5),
      Left(left),
      Right(4),
      Left(left),
      Right(3),
      Right(2),
      Left(left),
      Right(1)
    )
}
