package HT_3

import scala.io.Source
import scala.util.Try

object ControlStructures2 {
  // Homework

  // Create a command line application that reads various "commands" from the
  // stdin, evaluates them, and writes output to stdout.

  // Commands are:

  //   divide 4 5
  // which should output "4 divided by 5 is 0.8"

  //   sum 5 5 6 8.5
  // which should output "the sum of 5 5 6 8.5 is 24.5"

  //   average 4 3 8.5 4
  // which should output "the average of 4 3 8.5 4 is 4.875"

  //   min 4 -3 -17
  // which should output "the minimum of 4 -3 -17 is -17"

  //   max 4 -3 -17
  // which should output "the maximum of 4 -3 -17 is 4"

  // In case of commands that cannot be parsed or calculations that cannot be performed,
  // output a single line starting with "Error: "

  sealed trait Command

  object Command {
    final case class Divide(dividend: Double, divisor: Double) extends Command

    final case class Sum(numbers: List[Double]) extends Command

    final case class Average(numbers: List[Double]) extends Command

    final case class Min(numbers: List[Double]) extends Command

    final case class Max(numbers: List[Double]) extends Command
  }

  final case class ErrorMessage(value: String)

  // Adjust `Result` and `ChangeMe` as you wish - you can turn Result into a `case class` and remove the `ChangeMe` if
  // you think it is the best model for your solution, or just have other `case class`-es implement `Result`
  case class Result(res: Double) {
    def finalRes(x: String) = {
      val splitString = x.split(' ').toList
      splitString.head match {
        case "divide"          => s"${splitString.tail.head.toDouble} divided by ${splitString.tail(1).toDouble} is"
        case "average" | "sum" => s"the ${splitString.head} of ${splitString.tail.map(el => el.toDouble).mkString(" ")} is"
        case "min" | "max"     => s"the ${splitString.head}imum of ${splitString.tail.map(el => el.toDouble).mkString(" ")} is"
      }
    }
  }

  def parseCommand(x: String): Either[ErrorMessage, Command] = {
    val argumentsWithPossibleFail = x.split(' ').toList
    val arguments = if (argumentsWithPossibleFail.head == "divide" && argumentsWithPossibleFail.length != 3) argumentsWithPossibleFail.updated(0, "Error")
    else if (argumentsWithPossibleFail.length == 1) argumentsWithPossibleFail.updated(0, "Error")
    else argumentsWithPossibleFail
    Try(arguments.tail.map(el  => el.toDouble)).toEither match {
      case Right(listOfDouble) => arguments.head match {
        case "divide"          => Right(Command.Divide(listOfDouble(0), listOfDouble(1)))
        case "sum"             => Right(Command.Sum(listOfDouble))
        case "average"         => Right(Command.Average(listOfDouble))
        case "min"             => Right(Command.Min(listOfDouble))
        case "max"             => Right(Command.Max(listOfDouble))
        case _                 => Left(ErrorMessage("Error: Invalid input"))
      }
      case Left(_)             => Left(ErrorMessage("Error: Invalid input"))
    }
    // implement this method
    // Implementation hints:
    // You can use String#split, convert to List using .toList, then pattern match on:
    //   case x :: xs => ???

    // Consider how to handle extra whitespace gracefully (without errors).
  }

  // should return an error (using `Left` channel) in case of division by zero and other
  // invalid operations
  def calculate(x: Command): Either[ErrorMessage, Result] = {
    x match {
      case Command.Divide(dividend, divisor) => if (divisor != 0) Right(Result(dividend / divisor)) else Left(ErrorMessage("Error: Division by zero"))
      case Command.Sum(numbers)              => Right(Result(numbers.sum))
      case Command.Average(numbers)          => Right(Result(numbers.sum / numbers.length))
      case Command.Min(numbers)              => Right(Result(numbers.min))
      case Command.Max(numbers)              => Right(Result(numbers.max))
      case _                                 => Left(ErrorMessage("Error: You did something wrong"))
    }
    // implement this method
  }

  def renderResult(x: Result): String = x.res.toString
  // implement this method

  def process(x: String): String = {
    // the import above will enable useful operations on Either-s such as `leftMap`
    // (map over the Left channel) and `merge` (convert `Either[A, A]` into `A`),
    // but you can also avoid using them using pattern matching.
    (for {
      parsRes <- parseCommand(x)
      calcRes <- calculate(parsRes)
    } yield calcRes) match {
      case Left(er)   => er.value
      case Right(res) => s"${res.finalRes(x)} ${renderResult(res)}"
    }
    // implement using a for-comprehension
  }

  // This `main` method reads lines from stdin, passes each to `process` and outputs the return value to stdout
  def main(args: Array[String]): Unit = Source.stdin.getLines() map process foreach println
}