package vzijden.workout.domain.model

data class LoggedSet(
    val exercise: PlannedExercise,
    val loggedExerciseId: Int,
    var weight: Int,
    var loggedWorkoutId: Int
) {
  constructor(exercise: PlannedExercise, loggedExerciseId: Int, weight: Int, loggedWorkoutId: Int, unit: Int, id: Int) : this(exercise, loggedExerciseId, weight, loggedWorkoutId) {
    this.unit = unit
    this.id = id
  }

  var unit: Int = 0
    private set
  var id: Int = 0
    private set
}