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

import org.scalacheck.Gen
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers._

import java.lang.Math.abs
import scala.annotation.tailrec
object GCDReference {
  @tailrec
  def gcd(a: Int, b: Int): Option[Int] = {
    (abs(a), abs(b)) match {
      case (0, 0) => None
      case (m, 0) => Some(m)
      case (m, n) => gcd(n, m % n)
    }
  }
}

class GCDHiddenTest extends AnyFreeSpec with ScalaCheckPropertyChecks {
  private val smallInteger = Gen.choose(0, 10000)

  "GCD hidden" - {
    "works" in {
      forAll(smallInteger, smallInteger) { (a: Int, b: Int) =>
        GCD.gcd(a, b) shouldEqual GCDReference.gcd(a, b).getOrElse(0)
      }
    }
  }
}