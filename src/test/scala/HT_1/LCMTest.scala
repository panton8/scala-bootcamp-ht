package HT_1

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

class LCMTest extends AnyFreeSpec {
  "LCM" - {
    "lcm(4, 6)" in {
      LCM.lcm(4, 6) shouldEqual 12
    }
    "lcm(2, 9)" in {
      LCM.lcm(2, 9) shouldEqual 18
    }
    "lcm(-5, 18)" in {
      LCM.lcm(-5, 18) shouldEqual 90
    }
  }
}

import org.scalacheck.Gen
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers._

import java.lang.Math.abs

object LCMReference {
  def lcm(a: Int, b: Int): Option[Int] = {
    (abs(a), abs(b)) match {
      case (0, _) | (_, 0) => None
      case (m, n)          => GCDReference.gcd(a, b).map(m * n / _)
    }
  }
}

class LCMHiddenTest extends AnyFreeSpec with ScalaCheckPropertyChecks {
  private val smallInteger = Gen.choose(-10000, 10000)

  "LCM hidden" - {
    "works" in {
      forAll(smallInteger, smallInteger) { (a: Int, b: Int) =>
        LCM.lcm(a, b) shouldEqual LCMReference.lcm(a, b).getOrElse(0)
      }
    }
  }
}