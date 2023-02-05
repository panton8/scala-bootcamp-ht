package HT_4

object DataStructures {
  // Implement a special sort which sorts the keys of a map (K) according to their associated
  // values (V).
  //
  // In case of "ties" (equal values) it should group these keys K into Set-s in the results.
  //
  // The input is a map where keys (K) are the values to be sorted and values are their associated numeric
  // values.
  //
  // The output is a list (in ascending order according to the associated `Int` values) of tuples of `Set`-s
  // with values from K, and the associated value V for these values in the `Set`.
  //
  // For example:
  //
  // Input `Map("a" -> 1, "b" -> 2, "c" -> 4, "d" -> 1, "e" -> 0, "f" -> 2, "g" -> 2)` should result in
  // output `List(Set("e") -> 0, Set("a", "d") -> 1, Set("b", "f", "g") -> 2, Set("c") -> 4)`.
  def sortConsideringEqualValues[T](map: Map[T, Int]): List[(Set[T], Int)] = {
    val groupByValue = (for {
      (k, v) <- map
    } yield (Set(k), v)).toList.sortBy(_._2).groupBy(_._2)

    val uniteByValue = for {
      el <- groupByValue
    } yield if (el._2.length > 1) reduceList(el._2, el._2.length)
    else el._2
    (for {
      listWithValues <- uniteByValue
      valueFromList <- listWithValues
    } yield valueFromList).toList
  }

  private def reduceList[T](list: List[(Set[T], Int)], rep: Int): List[(Set[T], Int)] = rep match {
    case 1 => list
    case _ => reduceList(List(list.reduce((a, b) => (a._1 ++ b._1, a._2))), rep - 1)
  }
}
