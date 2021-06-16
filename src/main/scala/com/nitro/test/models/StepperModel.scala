package com.nitro.test.models

object StepperModel {
  type Direction = (Int, Int)
  type Steps = Int

  protected trait DirectionType {
    val direction: Direction
  }

  case object RightMovement extends DirectionType {
    override val direction: Direction = (1, 0)
  }

  case object UpMovement extends DirectionType {
    override val direction: Direction = (0, 1)
  }

  case object LeftMovement extends DirectionType {
    override val direction: Direction = (-1, 0)
  }

  case object DownMovement extends DirectionType {
    override val direction: Direction = (0, -1)
  }

  def calculateDirection(input: Direction): DirectionType = {
    input match {
      case (1, 0)  => RightMovement
      case (0, 1)  => UpMovement
      case (-1, 0) => LeftMovement
      case (0, -1) => DownMovement
    }
  }

  case class Stepper(steps: Steps, direction: DirectionType) {
    private final val AllowedDirections = Seq((1, 0), (0, 1), (-1, 0), (0, -1))

    require(Option(steps).getOrElse(0) >= 0, "Steps should be always positive")
    require(
      verifyDirections(),
      s"Incorrect directions, they should be ${AllowedDirections.mkString(",")}"
    )

    private def verifyDirections(): Boolean = {
      AllowedDirections.contains(Option(direction.direction).getOrElse((0, 0)))
    }
  }
}
