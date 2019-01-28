package vzijden.workout.domain.model

data class LoggedSet(
    val plannedSet: PlannedSet,
    var weight: Int,
    var loggedWorkoutId: Int
) {
  constructor(plannedSet: PlannedSet, weight: Int, loggedWorkoutId: Int, unit: Int, id: Int) : this(plannedSet, weight, loggedWorkoutId) {
    this.unit = unit
    this.id = id
  }

  var unit: Int = 0
    private set
  var id: Int = 0
    private set
}