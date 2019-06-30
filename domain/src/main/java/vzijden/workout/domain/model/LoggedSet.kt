package vzijden.workout.domain.model

data class LoggedSet(
    val exercise: Exercise,
    val loggedExerciseId: Long,
    var weight: Int,
    var loggedWorkoutId: Long,
    var reps: Int
) {
  constructor(exercise: Exercise, loggedExerciseId: Long, weight: Int, reps: Int, loggedWorkoutId: Long, unit: Int, id: Long) : this(exercise, loggedExerciseId, weight, loggedWorkoutId, reps) {
    this.unit = unit
    this.id = id
  }

  var unit: Int = 0
    private set
  var id: Long = 0
    private set
}