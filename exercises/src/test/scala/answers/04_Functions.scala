package exercises.answers

import minitest._

/*
 * ADT models data while Function models behaviour.
 * A function is simply something that accepts an input value
 * and produces an output value.
 * In more accademic terms it connects a Domain to a Codomain.
 * Functions are described/documented by it's type definition.
 *
 *  f:  InType => OutType
 */

object FunctionsTests extends SimpleTestSuite {

  val asString: Double => String = in => in.toString

  val parseString: String => Int = in => in.toInt

  val reciprocal: Int => Double = in => 1.0 / in

  // NOTE: composition by manually pass values between functions
  //       mix program description with program execution
  val reciprocalString: String => String = in => {
    val n: Int    = parseString(in)
    val d: Double = reciprocal(n)
    asString(d)
  }

  // NOTE: composition by connection functions and invoke only one
  //       split program description with program execution
  //       but still do both in the same moment
  val reciprocalString_II: String => String = in => {
    val f: String => Double = reciprocal.compose(parseString)
    val g: String => String = asString.compose(f)
    g(in)
  }

  // NOTE: composition by connection functions and invocation is up to the caller
  //       split program description with program execution
  //       definition and execution happends in different moments
  val reciprocalString_III: String => String =
    asString.compose(reciprocal).compose(parseString)

  test("from string to string throught reciprocal") {
    assertEquals(reciprocalString("42"), "0.023809523809523808")
  }
}
