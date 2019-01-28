package vzijden.workout.domain.model

import java.util.*

data class LoggedWorkout(
    val PlannedWorkout: PlannedWorkout,
    val date: Date
) {
  var id: Int = 0
  private set
  var finished: Boolean = false
  private set
  var loggedSets: List<LoggedSet>? = null
  private set

  constructor(PlannedWorkout: PlannedWorkout, date: Date, id: Int, finished: Boolean, loggedSet: List<LoggedSet>) : this(PlannedWorkout, date) {
    this.id = id
    this.finished = finished
    this.loggedSets = loggedSets
  }
}