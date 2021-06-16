package com.nitro.test.adapters.distance

object ManhattanDriverDistance extends DistanceCalculatorAdapter {

  /***
    * the distance between 2 points will be calculating calculating the distance in between the X axis (absolute)
    * plus the distance between the Y axis (absolute) so, adding those 2 numbers will give us the distance
    * @param pointOne Point one
    * @param pointTwo Point two
    * @return Manhattan Driver Distance calculator
    */
  override def calculateDistance(pointOne: (Int, Int),
                                 pointTwo: (Int, Int)): Int = {
    val (pointOneX, pointOneY) = pointOne
    val (pointTwoX, pointTwoY) = pointTwo

    Math.abs(pointOneX - pointTwoX) + Math.abs(pointOneY - pointTwoY)
  }
}
