package vzijden.workout.domain.model

data class PlannedWorkout(
  var scheduleId: Int,
  var name: String,
  var day: Int
) {
  var plannedExercises: List<PlannedExercise>? = null
  private set

  var history: List<LoggedWorkout>? = null
  private set

  var id: Int = 0
  private set

  constructor(scheduleId: Int, name: String, day: Int, plannedExercises: List<PlannedExercise>, history: List<LoggedWorkout>, id: Int) : this(scheduleId, name, day) {
    this.id = id
    this.plannedExercises = plannedExercises
    this.history = history
  }
}