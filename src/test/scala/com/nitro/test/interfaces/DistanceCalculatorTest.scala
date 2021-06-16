package com.nitro.test.interfaces

import com.nitro.test.adapters.distance.{
  DistanceCalculatorAdapter,
  ManhattanDriverDistance
}
import com.nitro.test.common.TestVariables._
import org.scalatest.flatspec.AnyFlatSpec

class DistanceCalculatorTest extends AnyFlatSpec {
  behavior of "DistanceCalculator"

  it should "calculate the distance properly for 2 givens points using Manhattan Driver Algo." in {
    assert(
      DistanceCalculator.calculateDistance(
        solutionMatrixThree(1),
        solutionMatrixThree(9),
        ManhattanDriverDistance
      ) == 2
    )
    assert(
      DistanceCalculator.calculateDistance(
        solutionMatrixThree(1),
        solutionMatrixThree(1),
        ManhattanDriverDistance
      ) == 0
    )
    assert(
      DistanceCalculator.calculateDistance(
        solutionMatrixThree(1),
        solutionMatrixThree(2),
        ManhattanDriverDistance
      ) == 1
    )
  }

  it should "be able to exchange the adapter" in {
    object Mock extends DistanceCalculatorAdapter {
      override def calculateDistance(pointOne: (Int, Int),
                                     pointTwo: (Int, Int)): Int = -1
    }

    assert(
      DistanceCalculator.calculateDistance(
        solutionMatrixThree(1),
        solutionMatrixThree(1),
        Mock
      ) == -1
    )
  }
}
