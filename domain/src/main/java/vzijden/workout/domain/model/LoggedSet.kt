package vzijden.workout.domain.model

data class LoggedSet(
    val plannedSet: PlannedSet,
    val loggedExerciseId: Int,
    var weight: Int,
    var loggedWorkoutId: Int
) {
  constructor(plannedSet: PlannedSet, loggedExerciseId: Int, weight: Int, loggedWorkoutId: Int, unit: Int, id: Int) : this(plannedSet, loggedExerciseId, weight, loggedWorkoutId) {
    this.unit = unit
    this.id = id
  }

  var unit: Int = 0
    private set
  var id: Int = 0
    private set
}