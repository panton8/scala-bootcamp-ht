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