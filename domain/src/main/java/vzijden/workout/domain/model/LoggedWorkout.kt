package vzijden.workout.domain.model

class LoggedWorkout internal constructor(id: Long, loggedSets: List<LoggedSet>) {
  var id: Long = id
    private set

  var loggedSets: List<LoggedSet> = loggedSets
    private set

}