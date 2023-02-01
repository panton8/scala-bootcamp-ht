package HT_1

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

class GCDTest extends AnyFreeSpec {
  "GCD" - {
    "gcd(54, 24)" in {
      GCD.gcd(54, 24) shouldEqual 6
    }
    "gcd(147, 24)" in {
      GCD.gcd(147, 24) shouldEqual 3
    }
    "gcd(-1, 12)" in {
      GCD.gcd(-1, 12) shouldEqual 1
    }
  }
}