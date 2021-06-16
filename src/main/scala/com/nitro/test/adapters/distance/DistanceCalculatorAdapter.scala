package com.nitro.test.adapters.distance

trait DistanceCalculatorAdapter {
  def calculateDistance(pointOne: (Int, Int), pointTwo: (Int, Int)): Int
}
