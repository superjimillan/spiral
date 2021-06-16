package com.nitro.test.adapters.matrix

import com.nitro.test.models.StepperModel._
import com.nitro.test.common.TestVariables._
import org.scalatest.flatspec.AnyFlatSpec

class SpiralMatrixBuilderAdapterTest extends AnyFlatSpec {
  val calculator = new SpiralMatrixBuilderAdapter()

  behavior of ("SpiralGenerator")

  it should "Construct the class based on different inputs (odd values)" in {
    Seq(1, 3, 5, 29).foreach(
      n =>
        assert(
          new SpiralMatrixBuilderAdapter(Some(n)).quadraticDimension.contains(n)
      )
    )
  }

  it should "Construct the class accepting a null value as input" in {
    assert(new SpiralMatrixBuilderAdapter().quadraticDimension.isEmpty)
    assert(new SpiralMatrixBuilderAdapter(None).quadraticDimension.isEmpty)
  }

  it should "Construct the class accepting a valid dimension " in {
    (1 to 10).foreach(
      destination =>
        assert(
          new SpiralMatrixBuilderAdapter(Some(5)).quadraticDimension
            .contains(5)
      )
    )
  }

  it should "Generate IllegalInputParameterException when inputs are even values" in {
    Seq(0, 2, 4, 22).foreach(
      n =>
        intercept[IllegalArgumentException](
          new SpiralMatrixBuilderAdapter(Some(n))
      )
    )
  }

  it should "Generate IllegalInputParameterException when dimension is defined but destination is bigger than max no" in {
    assert(!new SpiralMatrixBuilderAdapter(Some(3)).limitVerification(15))
    assert(!new SpiralMatrixBuilderAdapter(Some(5)).limitVerification(26))
    assert(!new SpiralMatrixBuilderAdapter(Some(3)).limitVerification(15))
  }

  it should "Generate spiral matrix Stepper with centered values" in {
    def helperCalculateDirections(dimension: Int) = {
      // 0,0 as axe
      val baseDirection =
        Seq(RightMovement, DownMovement, LeftMovement, UpMovement)
      List.fill((dimension - 1) / 2)(baseDirection).flatten :+ RightMovement
    }

    //amount of steps to generate the matrix is (dimension * 2) - 1
    val matrixThree = calculator.spiralStepsGenerator(3)
    val matrixFive = calculator.spiralStepsGenerator(5)
    val matrixSeven = calculator.spiralStepsGenerator(7)

    assert(matrixThree.length == 5)
    assert(helperCalculateDirections(3) == matrixThree.map(_.direction))
    assert(matrixFive.length == 9)
    assert(helperCalculateDirections(5) == matrixFive.map(_.direction))
    assert(matrixSeven.length == 13)
    assert(helperCalculateDirections(7) == matrixSeven.map(_.direction))
  }

  it should "capture exceptions generating spiral steps" in {
    intercept[IllegalArgumentException](calculator.spiralStepsGenerator(-1))
    intercept[IllegalArgumentException](calculator.spiralStepsGenerator(0))
    intercept[IllegalArgumentException](calculator.spiralStepsGenerator(2))
    intercept[IllegalArgumentException](calculator.spiralStepsGenerator(6))
  }

  it should "fill the matrix properly" in {
    val matrixThree = calculator.fillTheMatrix(3)
    val matrixFive = calculator.fillTheMatrix(5)

    assert(matrixThree.keys.size == solutionMatrixThree.keys.size)
    solutionMatrixThree.keys.foreach(
      number => assert(solutionMatrixThree(number) == matrixThree(number))
    )
    assert(matrixFive.keys.size == solutionMatrixFive.keys.size)
    solutionMatrixFive.keys.foreach(
      number => assert(solutionMatrixFive(number) == matrixFive(number))
    )
  }

  it should "render the matrix using predefined dimensions or dynamically based on the number to get its distance" in {
    assert(new SpiralMatrixBuilderAdapter(Some(5)).getMatrix().keys.size == 25)
    assert(new SpiralMatrixBuilderAdapter().getMatrix(Some(9)).keys.size == 25)
    assert(new SpiralMatrixBuilderAdapter().getMatrix(Some(8)).keys.size == 9)
    intercept[IllegalArgumentException](
      new SpiralMatrixBuilderAdapter().getMatrix()
    )
  }
}
