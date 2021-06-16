package com.nitro.test.models

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Random

class StepperModelTest extends AnyFlatSpec {
  val validStep = StepperModel.Stepper(1, StepperModel.RightMovement)

  behavior of ("Stepper")

  it should "should instantiate a valid object" in {
    val random = new Random()
    val AllowedDirections = Seq(
      StepperModel.RightMovement,
      StepperModel.LeftMovement,
      StepperModel.UpMovement,
      StepperModel.DownMovement
    )
    (1 to 10).foreach(step => {
      val indexRandom = random.nextInt(AllowedDirections.length)
      assert(
        StepperModel.Stepper(step, AllowedDirections(indexRandom)).steps == step
      )
    })
  }

  it should "capture failures in the steps" in {
    intercept[IllegalArgumentException](validStep.copy(steps = -1))
    intercept[IllegalArgumentException](validStep.copy(steps = -100))
  }

}
