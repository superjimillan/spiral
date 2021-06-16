package com.nitro.test.adapters.matrix

import com.nitro.test.models.StepperModel._

import scala.annotation.tailrec

class SpiralMatrixBuilderAdapter(val quadraticDimension: Option[Int] = None)
    extends MatrixBuilderAdapter {
  require(
    quadraticDimension.getOrElse(1) % 2 != 0,
    "Dimension must be a positive odd number"
  )

  override def getMatrix(
    distance: Option[Steps] = None
  ): Map[Int, Coordinates] = {
    val dimension = calculateDimension(distance.getOrElse(0))
    fillTheMatrix(dimension)
  }

  /***
    * Generates the steps and the directions that we need to take to generate the matrix starting from the center:
    * directions:
    *  x:
    *   > 0 => movement to the right
    *   < 0 => movement to the left
    *  y:
    *   > 0 => movement down
    *   < 0 => movement up
    *
    * 0 means no directions
    * @param dimension Matrix dimension
    * @return Ordered list of steps to generate the matrix
    */
  def spiralStepsGenerator(dimension: Int): Seq[Stepper] = {
    if (dimension < 1 || (dimension % 2) == 0)
      throw new IllegalArgumentException(
        "Method spiralGenerator accepts only positive and odd parameters"
      )
    (1 until dimension)
      .flatMap { steps =>
        Seq(
          Stepper(
            steps,
            calculateDirection((math.pow(-1, steps + 1).toInt, 0))
          ),
          Stepper(steps, calculateDirection((0, math.pow(-1, steps).toInt)))
        )
      }
      .:+(Stepper(dimension - 1, RightMovement))
  }

  /***
    * Verifies if destination exists in the generated matrix in case dimension is defined
    * @param destination Int
    * @return True if destination exists in the matrix
    */
  def limitVerification(destination: Int): Boolean = {
    if (quadraticDimension.isEmpty) true
    else {
      val maxNumberAllowedToDistance =
        math.pow(quadraticDimension.getOrElse(1).toDouble, 2).toInt
      maxNumberAllowedToDistance >= destination
    }
  }

  /***
    * It returns the dimension for the matrix
    * @param distance
    * @return Dimension of the matrix set during object instance or minimum matrix dimension
    */
  def calculateDimension(distance: Int): Int = {
    if (distance == 0 && quadraticDimension.isEmpty)
      throw new IllegalArgumentException(
        "Error building spiral matrix: At least dimension or number to calculate distance must be provided"
      )
    if (distance != 0 && quadraticDimension.isDefined && !limitVerification(
          distance
        ))
      throw new IllegalArgumentException(
        "Number to calculate distance is not contained in generated matrix"
      )
    quadraticDimension.getOrElse({
      val min = math.sqrt(distance).toInt + 1
      if (min % 2 == 0) min + 1
      else min
    })
  }

  def fillTheMatrix(dimension: Int): Map[Int, Coordinates] = {
    if (dimension % 2 == 0 || dimension < 0)
      throw new IllegalArgumentException(
        "dimension must be odd and positive value"
      )

    val origin = Map(1 -> (dimension / 2, dimension / 2))
    fillTheMatrixRecursive(spiralStepsGenerator(dimension), origin)
  }

  @tailrec
  private def fillTheMatrixRecursive(
    input: Seq[Stepper],
    output: Map[Int, Coordinates]
  ): Map[Int, Coordinates] =
    if (input.isEmpty) output
    else {
      val maxValueInOutput = output.keys.max // this is the bigger number in the matrix
      val (maxValueCoordinateX, maxValueCoordinateY) = output(maxValueInOutput)
      val currentIteration = input.head // this is the current step that we are going to take
      val (currentIterationDirectionX, currentIterationDirectionY) =
        currentIteration.direction.direction
      val newElementsToBeFilled = (1 to currentIteration.steps).flatMap {
        value =>
          Map(
            maxValueInOutput + value -> (maxValueCoordinateX + (currentIterationDirectionX * value),
            maxValueCoordinateY + (currentIterationDirectionY * value))
          )
      }.toMap

      fillTheMatrixRecursive(input.drop(1), output ++ newElementsToBeFilled)
    }
}
