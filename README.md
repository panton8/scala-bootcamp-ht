# Scala-bootcamp-ht

## Homework 1 - Basics assignment

Implement functions that calculate [LCM](https://en.wikipedia.org/wiki/Least_common_multiple) and [GCD](https://en.wikipedia.org/wiki/Greatest_common_divisor) for integers.

## Homework 2 - Control Structures

Implement some functions using: if-else, pattern matching

## Homework 3 - Control Structures

Create a command line application that reads various "commands" from the
stdin, evaluates them, and writes output to stdout.

Commands are:
```
divide 4 5
```  
which should output `4 divided by 5 is 0.8`

```
sum 5 5 6 8.5
```
which should output `the sum of 5 5 6 8.5 is 24.5`

```
average 4 3 8.5 4
```
which should output `the average of 4 3 8.5 4 is 4.875`

```
min 4 -3 -17
```
which should output `the minimum of 4 -3 -17 is -17`

```
max 4 -3 -17
```
which should output `the maximum of 4 -3 -17 is 4`

In case of commands that cannot be parsed or calculations that cannot be performed,
output a single line starting with `Error: `

## Homework 4 - Functions and ADTs

Implement a special sort which sorts the keys of a map (K) according to their associated values (V).

In case of "ties" (equal values) it should group these keys K into Set-s in the results.

The input is a map where keys (K) are the values to be sorted and values are their associated numeric values.

The output is a list (in ascending order according to the associated `Int` values) of tuples of `Set`-s with values from K, and the associated value V for these values in the `Set`.

For example:

Input `Map("a" -> 1, "b" -> 2, "c" -> 4, "d" -> 1, "e" -> 0, "f" -> 2, "g" -> 2)` should result in output `List(Set("e") -> 0, Set("a", "d") -> 1, Set("b", "f", "g") -> 2, Set("c") -> 4)`.

## Homework 5 - Functions and ADTs

Implement logic for processing CSV data containing car details, including information about manufacturer,
model, production year, license plate number and engine type.

The processing logic should work as follows:

* raw CSV data is submitted as input;
* if invalid, encountered failures are returned as output;
* if valid, CSV data gets parsed into cars, sorted according to the provided criteria and returned as output.

Example of a valid CSV data line:

```text
BMW,i3,2019,AB1234,E
```

## Homework 6 - Error Handling

Implement logic for validating payment card details, including number, expiration date, name and security
code. The validation logic should work as follows:

* raw strings are supplied as input;
* each string is validated;
* if all supplied strings are valid, a payment card is constructed and returned;
* if one or more of the supplied strings are invalid, all encountered errors are aggregated and returned.
