package vzijden.workout.domain.model

data class PlannedWorkout(
  var scheduleId: Int,
  var name: String,
  var day: Int
) {
  var plannedExercises: List<PlannedExercise> = listOf()
  private set

  var id: Int = 0
  private set

  constructor(scheduleId: Int, name: String, day: Int, plannedExercises: List<PlannedExercise>, id: Int) : this(scheduleId, name, day) {
    this.id = id
    this.plannedExercises = plannedExercises
  }
}