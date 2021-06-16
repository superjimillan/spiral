package com.nitro.test.interfaces

import com.nitro.test.adapters.distance.DistanceCalculatorAdapter

object DistanceCalculator {

  /**
    * Given 2 points, we can calculate the distance using the desired algorithm
    * @param pointOne Point One
    * @param pointTwo Point Two
    * @param distanceCalculatorAdapter Adapter
    * @return Int value with the distance
    */
  def calculateDistance(
    pointOne: (Int, Int),
    pointTwo: (Int, Int),
    distanceCalculatorAdapter: DistanceCalculatorAdapter
  ): Int = distanceCalculatorAdapter.calculateDistance(pointOne, pointTwo)
}
