package com.nitro.test.interfaces

import com.nitro.test.adapters.matrix.{
  MatrixBuilderAdapter,
  SpiralMatrixBuilderAdapter
}
import com.nitro.test.common.TestVariables._

import org.scalatest.flatspec.AnyFlatSpec

class MatrixComposerTest extends AnyFlatSpec {
  behavior of "MatrixCalculator"

  it should "be able to render an spiral matrix centered starting with one" in {

    val result =
      MatrixComposer.getMatrix(new SpiralMatrixBuilderAdapter(Some(3)))
    assert(result.keys.size == solutionMatrixThree.keys.size)
    solutionMatrixThree.keys.foreach(
      number => assert(result(number) == solutionMatrixThree(number))
    )
  }

  it should "capture all the possible exceptions generating the spiral matrix" in {
    intercept[IllegalArgumentException](
      MatrixComposer.getMatrix(new SpiralMatrixBuilderAdapter())
    )
    intercept[IllegalArgumentException](
      MatrixComposer.getMatrix(new SpiralMatrixBuilderAdapter(Some(0)))
    )
    intercept[IllegalArgumentException](
      MatrixComposer.getMatrix(new SpiralMatrixBuilderAdapter(Some(-1)))
    )
    intercept[IllegalArgumentException](
      MatrixComposer
        .getMatrix(new SpiralMatrixBuilderAdapter(Some(3)), Some(88))
    )
  }

  it should "be able to exchange the calculator with other adapters" in {
    class Mock() extends MatrixBuilderAdapter {
      override def getMatrix(distance: Option[Int]): Map[Int, (Int, Int)] =
        Map(1 -> (0, 0))
    }

    assert(MatrixComposer.getMatrix(new Mock()).keys.size == 1)
    assert(MatrixComposer.getMatrix(new Mock())(1) == (0, 0))
  }
}
