package vzijden.workout.domain.model

data class LoggedExercise(
    val exerciseId: Int
) {
  constructor(id: Int, loggedSets: List<LoggedSet>, exercise: Exercise, exerciseId: Int) : this(exerciseId) {
    this.id = id
    this.loggedSets = loggedSets
    this.exercise = exercise
  }

  var loggedSets: List<LoggedSet> = listOf()

  var id: Int = 0
    private set

  var index: Int = 0
    private set

  var exercise: Exercise? = null
}