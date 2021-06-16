package com.nitro.test.adapters.distance

import com.nitro.test.common.TestVariables._
import org.scalatest.flatspec.AnyFlatSpec

class ManhattanDriverDistanceTest extends AnyFlatSpec {
  behavior of "ManhattanDriverDistance"

  it should "calculate different distances" in {
    assert(ManhattanDriverDistance.calculateDistance((0, 0), (0, 0)) == 0)
    assert(ManhattanDriverDistance.calculateDistance((0, 0), (0, 1)) == 1)
    assert(ManhattanDriverDistance.calculateDistance((1, 0), (0, 1)) == 2)
  }

  it should "calculate the distance between 2 points using a real 5x5 spiral matrix" in {
    assert(
      ManhattanDriverDistance
        .calculateDistance(solutionMatrixFive(1), solutionMatrixFive(12)) == 3
    )
    assert(
      ManhattanDriverDistance
        .calculateDistance(solutionMatrixFive(1), solutionMatrixFive(23)) == 2
    )
  }
}
