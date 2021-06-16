package com.nitro.test.interfaces

import com.nitro.test.adapters.matrix.MatrixBuilderAdapter

object MatrixComposer {

  /***
    * Interface to Render bidirectional matrix.
    * @param matrixBuilder Object to build the matrix
    * @param distanceToCalculate in case there is no more data than the distance to calculate, it will render the smaller matrix that contains that number
    * @return matrix description
    */
  def getMatrix(
    matrixBuilder: MatrixBuilderAdapter,
    distanceToCalculate: Option[Int] = None
  ): Map[Int, (Int, Int)] = {
    matrixBuilder.getMatrix(distanceToCalculate)
  }
}
