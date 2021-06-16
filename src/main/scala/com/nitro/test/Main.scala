package com.nitro.test
import com.nitro.test.adapters.distance.ManhattanDriverDistance
import com.nitro.test.adapters.matrix.SpiralMatrixBuilderAdapter
import com.nitro.test.interfaces.{DistanceCalculator, MatrixComposer}

import scala.io.StdIn._

object Main extends App {
  def getInputFromCommandLine: Option[Int] =
    try {
      Some(readInt())
    } catch {
      case _: Exception => None
    }

  println(
    "Introduce the dimension for the desired matrix (leave it empty for dynamic calculation):"
  )
  val matrixDimension = getInputFromCommandLine

  println(
    "Introduce the number in the defined matrix to calculate the distance with the origin:"
  )

  val distanceTo = getInputFromCommandLine

  try {
    val matrix = MatrixComposer.getMatrix(
      new SpiralMatrixBuilderAdapter(matrixDimension),
      distanceTo
    )
    val result = DistanceCalculator.calculateDistance(
      matrix(1),
      matrix(distanceTo.getOrElse(-1)),
      ManhattanDriverDistance
    )
    println(
      s"The distance between point 1 and point ${distanceTo.getOrElse(-1)} using a quadratic matrix with dimension ${math
        .sqrt(matrix.keys.size)
        .toInt} is ${result}"
    )
  } catch {
    case ex: Exception => println(s"ERROR: ${ex.getMessage}")
  }
}
