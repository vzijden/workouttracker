package vzijden.workout.domain.model

data class LoggedExercise(
    val exerciseId: Int
) {
  constructor(id: Int, loggedSets: List<LoggedSet> , exerciseId: Int) : this(exerciseId) {
    this.id = id
    this.loggedSets = loggedSets
  }

  var loggedSets: List<LoggedSet> = listOf()

  var id: Int = 0
    private set
}