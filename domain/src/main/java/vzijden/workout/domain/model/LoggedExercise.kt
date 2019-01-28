package vzijden.workout.domain.model

data class LoggedExercise(
    val plannedExercise: PlannedExercise,
    val loggedSet: List<LoggedSet>
) {
  constructor(plannedExercise: PlannedExercise, loggedSet: List<LoggedSet>, id: Int): this(plannedExercise, loggedSet) {
    this.id = id
  }
  var id: Int = 0
  private set
}