package vzijden.workout.domain.model

data class LoggedExercise(
    val plannedExercise: PlannedExercise,
    val loggedSet: List<LoggedSet>,
    val loggedWorkoutId: Long
) {
  constructor(plannedExercise: PlannedExercise, loggedSet: List<LoggedSet>, loggedWorkoutId: Long, id: Int): this(plannedExercise, loggedSet, loggedWorkoutId) {
    this.id = id
  }
  var id: Int = 0
  private set
}