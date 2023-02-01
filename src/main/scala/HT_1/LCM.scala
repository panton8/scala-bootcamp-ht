package HT_1

object LCM {
  /**
   * Implement https://en.wikipedia.org/wiki/Least_common_multiple for integers.
   *
   * Return 0 if LCM is undefined.
   */
  def lcm(a: Int, b: Int): Int = {
    if (a == 0 && b == 0) 0 else Math.abs(a * b)/ GCD.gcd(a, b)
  }
}
