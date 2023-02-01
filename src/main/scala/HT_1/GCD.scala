package HT_1

object GCD {
  /**
   * Implement https://en.wikipedia.org/wiki/Greatest_common_divisor for integers.
   *
   * Return 0 if GCD is undefined.
   */
  def gcd(a: Int, b: Int): Int = {
    if (a == 0 && b == 0) 0
    if (b == 0) Math.abs(a) else gcd(b, a % b)
  }
}