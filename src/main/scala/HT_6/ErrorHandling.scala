package HT_6

import HT_6.ErrorHandling.ValidationError.{ExpirationDateHasWrongFormat, ExpirationDateIsInThePast, NameContainsInvalidCharacters, NameIsOutOfRange, NameStartsOrEndsWithSpace, NumberContainsInvalidCharacters, NumberIsOutOfRange, NumberStartsWithZero, SecurityCodeIsInvalid}
import cats.data.{NonEmptyChain, NonEmptySet, Validated, ValidatedNec, ValidatedNel}
import cats.syntax.all._

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, YearMonth}

/** Implement all methods marked with `???` below according to their Scaladoc description.
 * Add helper methods and more unit tests in a separate file if necessary to properly verify the solution.
 */
object ErrorHandling {

  type AllErrorsOr[A] = ValidatedNec[ValidationError, A]

  /** Payment card number. */
  final case class Number(value: String) extends AnyVal
  object Number {

    /** Constructs [[Number]] from the string.
     * <p/>
     * The string must contain between 8 and 19 digits (inclusive) and must not start with 0.
     */
    def fromString(s: String): AllErrorsOr[Number] = {
      def correctLen: AllErrorsOr[String] =
        Validated.cond(s.length >= 8 && s.length <= 19, s, NumberIsOutOfRange).toValidatedNec

      def correctFirstSymbol: AllErrorsOr[String] =
        Validated.cond(!s.startsWith("0"), s, NumberStartsWithZero).toValidatedNec

      def isNum: AllErrorsOr[String] = {
        Validated
          .catchNonFatal(s.toLong)
          .map(s => s.toString)
          .leftMap[ValidationError](_ => NumberContainsInvalidCharacters)
          .toValidatedNec
      }

      (correctLen *> correctFirstSymbol *> isNum).map(s => Number(s))
    }
  }

  /** Payment card expiration date. */
  final case class ExpirationDate(value: YearMonth) extends AnyVal
  object ExpirationDate {

    /** Constructs [[ExpirationDate]] from the string.
     * <p/>
     * The string must be in the format `MM/YYYY`. `MM` part must be a pair of digits from "01" to "12".
     * `YYYY` part must be a pair of digits from "0001" to "9999". For example, `02/2024` means the card
     * is active until the last day of February 2024.
     * <p/>
     * [[ExpirationDate]] must not be in the past, i.e. before the current [[YearMonth]], as supplied
     * by the `now` function.
     *
     * @param now the function that returns the current [[YearMonth]]
     */
    def fromString(s: String)(now: () => YearMonth): AllErrorsOr[ExpirationDate] = {
      Validated
        .catchNonFatal(LocalDate.parse("01/" + s , DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .leftMap[ValidationError](e => ExpirationDateHasWrongFormat)
        .andThen(date =>
          Validated.cond(
            date.plusMonths(1).isAfter(now().atDay(1)),
            ExpirationDate(YearMonth.of(date.getYear, date.getMonthValue)),
            ExpirationDateIsInThePast
          )
        ).toValidatedNec
    }

  }

  /** Payment card holder name. */
  final case class Name(value: String) extends AnyVal
  object Name {

    /** Constructs [[Name]] from the string.
     * <p/>
     * The string must be between 2 and 26 characters (inclusive). It must contain only spaces, upper and
     * lower case letters. It must not start or end with a space.
     */
    def fromString(s: String): AllErrorsOr[Name] = {
      def checkLen: AllErrorsOr[String] =
        Validated.cond(s.length >= 2 && s.length <= 26, s, NameIsOutOfRange).toValidatedNec

      def validFirstAndLastSymbols: AllErrorsOr[String] =
        Validated.cond(!s.startsWith(" ") && !s.endsWith(" "), s, NameStartsOrEndsWithSpace).toValidatedNec

      def validKindOfSymbols: AllErrorsOr[String] =
        Validated.cond(s.matches("^[A-Za-z- ]*$"), s, NameContainsInvalidCharacters).toValidatedNec

      (checkLen *> validFirstAndLastSymbols *> validKindOfSymbols).map(s => Name(s))
    }
  }

  /** Payment card security code. */
  final case class SecurityCode(value: String) extends AnyVal
  object SecurityCode {

    /** Constructs [[SecurityCode]] from the string.
     * <p/>
     * The string must contain between 3 and 4 digits (inclusive).
     */
    def fromString(s: String): AllErrorsOr[SecurityCode] = {
      Validated.cond(s.matches("^[0-9]{3,4}$"), SecurityCode(s), SecurityCodeIsInvalid).toValidatedNec
    }
  }

  /** Payment card. */
  final case class PaymentCard(
                                number: Number,
                                expirationDate: ExpirationDate,
                                name: Name,
                                securityCode: SecurityCode,
                              )

  /** Payment card validation error. */
  sealed trait ValidationError
  object ValidationError {

    /** Payment card number contains less than 8 or more than 19 characters. */
    final case object NumberIsOutOfRange extends ValidationError

    /** Payment card number contains characters other than digits. */
    final case object NumberContainsInvalidCharacters extends ValidationError

    /** Payment card number starts with 0. */
    final case object NumberStartsWithZero extends ValidationError

    /** Expiration date is not in the format "MM/YYYY". */
    final case object ExpirationDateHasWrongFormat extends ValidationError

    /** Expiration date is in the past, i.e. earlier than the current month of the current year. */
    final case object ExpirationDateIsInThePast extends ValidationError

    /** Payment card name contains less than 2 or more than 26 characters. */
    final case object NameIsOutOfRange extends ValidationError

    /** Payment card name starts or ends with a space. */
    final case object NameStartsOrEndsWithSpace extends ValidationError

    /** Payment card name contains characters other than spaces, upper and lower case letters. */
    final case object NameContainsInvalidCharacters extends ValidationError

    /** Payment card security code is invalid. */
    final case object SecurityCodeIsInvalid extends ValidationError
  }

  object PaymentCardValidator {

    /** Attempts to construct [[PaymentCard]] from the supplied raw strings. Aggregates all encountered
     * validation errors. Returns as many errors as possible for any given set of input parameters.
     * <p/>
     * For example, if the supplied number is "0ABC", while the security code is "DEF", the returned errors
     * must include [[NumberIsOutOfRange]], [[NumberContainsInvalidCharacters]], [[NumberStartsWithZero]]
     * and [[SecurityCodeIsInvalid]].
     *
     * @param now the function that returns the current [[YearMonth]]
     *
     * @return [[PaymentCard]] or all encountered validation errors.
     */
    def validate(
                  number: String,
                  expirationDate: String,
                  name: String,
                  securityCode: String,
                )(now: () => YearMonth): AllErrorsOr[PaymentCard] = {
      (Number.fromString(number),
        ExpirationDate.fromString(expirationDate)(now),
        Name.fromString(name),
        SecurityCode.fromString(securityCode)).mapN(PaymentCard)
    }
  }
}