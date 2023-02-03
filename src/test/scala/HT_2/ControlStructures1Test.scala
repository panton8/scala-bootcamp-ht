package HT_2


import ControlStructures1._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.EitherValues._

class ControlStructures1Test extends AnyFreeSpec {

  def isAdultTests(isAdult: Int => Either[Error, Boolean]) = {
    "should return boolean for valid input" - {
      "for input 20" in {
        isAdult(20).value shouldBe true
      }

      "for input 13" in {
        isAdult(13).value shouldBe false
      }
    }

    "should return error for invalid input" - {
      "for input 99999" in {
        isAdult(99999).left.value shouldBe "99999 is too high, are you human?"
      }

      "for input -20" in {
        isAdult(-20).left.value shouldBe "-20 is negative, we do not serve unborn people"
      }
    }
  }

  "isAdult_if" - {
    isAdultTests(isAdultIf)
  }

  "isAdult_match" - {
    isAdultTests(isAdultMatch)
  }

  "isValidTriangle" - {
    "should determine if triangle is valid" - {
      "for input (2, 2, 3)" in {
        isValidTriangle(2, 2, 3) shouldBe true
      }

      "for input (2, 2, 0)" in {
        isValidTriangle(2, 2, 0) shouldBe false
      }

      "for input (2, 2, 4)" in {
        isValidTriangle(2, 2, 4) shouldBe false
      }
    }
  }

  "isValidCandidate" - {
    "should determine that candidate is valid" - {
      "for input (\"Wakanda\", 10, 10, true, 100)" in {
        isValidCandidate("Wakanda", 10, 10, true, 100) shouldBe true
      }
    }

    "should determine that candidate is not valid" - {
      "for input (\"US\", 5, 1, false, 0)" in {
        isValidCandidate("US", 5, 1, false, 0) shouldBe false
      }
    }
  }

}

class ControlStructuresHomework1HiddenTest extends AnyFreeSpec {

  def isAdultTests(isAdult: Int => Either[Error, Boolean]) = {
    "should return boolean for valid input" - {
      "for input 18" in {
        isAdult(18).value shouldBe true
      }

      "for input 100" in {
        isAdult(100).value shouldBe true
      }

      "for input 150" in {
        isAdult(150).value shouldBe true
      }

      "for input 17" in {
        isAdult(17).value shouldBe false
      }

      "for input 0" in {
        isAdult(0).value shouldBe false
      }
    }

    "should return error for invalid input" - {
      "for input 151" in {
        isAdult(151).left.value shouldBe "151 is too high, are you human?"
      }

      "for input -1" in {
        isAdult(-1).left.value shouldBe "-1 is negative, we do not serve unborn people"
      }
    }
  }

  "isAdult_if" - {
    isAdultTests(isAdultIf)
  }

  "isAdult_match" - {
    isAdultTests(isAdultMatch)
  }

  "isValidTriangle" - {
    "should determine if triangle is valid" - {
      "for input (100, 100, 100)" in {
        isValidTriangle(100, 100, 100) shouldBe true
      }

      "for input (100, 100, 1000000)" in {
        isValidTriangle(100, 100, 1000000) shouldBe false
      }

      "for input (2, 2, -2)" in {
        isValidTriangle(2, 2, -2) shouldBe false
      }

      "for input (0, 0, 0)" in {
        isValidTriangle(0, 0, 0) shouldBe false
      }
    }
  }

  "isValidCandidate" - {
    "should determine that candidate is valid" - {
      "for input (\"US\", 10, 10, false, 0)" in {
        isValidCandidate("US", 10, 10, false, 0) shouldBe true
      }

      "for input (\"US\", 10, 5, false, 0)" in {
        isValidCandidate("US", 10, 5, false, 0) shouldBe true
      }

      "for input (\"US\", 11, 4, false, 0)" in {
        isValidCandidate("US", 11, 4, false, 0) shouldBe true
      }

      "for input (\"US\", 5, 5, true, 100)" in {
        isValidCandidate("US", 5, 5, true, 100) shouldBe true
      }

      "for input (\"Wakanda\", 10, 2, false, 0)" in {
        isValidCandidate("Wakanda", 10, 2, false, 0) shouldBe true
      }

      "for input (\"Skyrim\", 10, 4, false, 0)" in {
        isValidCandidate("Skyrim", 10, 4, false, 0) shouldBe true
      }

      "for input (\"Narnia\", 10, 4, false, 0)" in {
        isValidCandidate("Narnia", 10, 4, false, 0) shouldBe true
      }

      "for input (\"Amestris\", 10, 4, false, 0)" in {
        isValidCandidate("Amestris", 10, 4, false, 0) shouldBe true
      }

      "for input (\"US\", 10, 4, false, 10)" in {
        isValidCandidate("US", 10, 4, false, 10) shouldBe true
      }

      "for input (\"US\", 10, 3, false, 100)" in {
        isValidCandidate("US", 10, 3, false, 100) shouldBe true
      }

      "for input (\"US\", 10, 0, false, 100000)" in {
        isValidCandidate("US", 10, 0, false, 100000) shouldBe true
      }

      "for input (\"US\", 6, 0, false, 1000000000)" in {
        isValidCandidate("US", 6, 0, false, 1000000000) shouldBe true
      }
    }

    "should determine that candidate is not valid" - {
      "for input (\"US\", 10, 4, false, 0)" in {
        isValidCandidate("US", 10, 4, false, 0) shouldBe false
      }

      "for input (\"US\", 5, 5, true, 10)" in {
        isValidCandidate("US", 5, 5, true, 10) shouldBe false
      }

      "for input (\"US\", 5, 5, false, 100)" in {
        isValidCandidate("US", 5, 5, false, 100) shouldBe false
      }

      "for input (\"US\", 7, 10, false, 100)" in {
        isValidCandidate("US", 7, 10, false, 100) shouldBe false
      }

      "for input (\"US\", 7, 6, false, 100)" in {
        isValidCandidate("US", 7, 6, false, 100) shouldBe false
      }

      "for input (\"Wakanda\", 4, 10, true, 10)" in {
        isValidCandidate("Wakanda", 4, 10, true, 10) shouldBe false
      }

      "for input (\"Wakanda\", 9, 2, false, 0)" in {
        isValidCandidate("Wakanda", 9, 2, false, 0) shouldBe false
      }

      "for input (\"Skyrim\", 10, 3, false, 0)" in {
        isValidCandidate("Skyrim", 10, 3, false, 0) shouldBe false
      }

      "for input (\"Narnia\", 10, 3, false, 0)" in {
        isValidCandidate("Narnia", 10, 3, false, 0) shouldBe false
      }

      "for input (\"Amestris\", 10, 3, false, 0)" in {
        isValidCandidate("Amestris", 10, 3, false, 0) shouldBe false
      }

      "for input (\"US\", 10, 4, false, 9)" in {
        isValidCandidate("US", 10, 4, false, 9) shouldBe false
      }

      "for input (\"US\", 10, 3, false, 99)" in {
        isValidCandidate("US", 10, 3, false, 99) shouldBe false
      }

      "for input (\"US\", 10, 0, false, 99999)" in {
        isValidCandidate("US", 10, 0, false, 99999) shouldBe false
      }

      "for input (\"US\", 6, 0, false, 999999999)" in {
        isValidCandidate("US", 6, 0, false, 999999999) shouldBe false
      }
    }
  }

}