package com.nitro.test.adapters.matrix

trait MatrixBuilderAdapter {
  type Coordinates = (Int, Int)

  def getMatrix(distance: Option[Int] = None): Map[Int, Coordinates]
}
