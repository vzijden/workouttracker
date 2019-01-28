package vzijden.workout.domain.model

import java.util.*

data class LoggedWorkout(
    val date: Date,
    val plannedWorkoutId: Int
) {
  var id: Int = 0
  private set
  var finished: Boolean = false
  private set
  var loggedSets: List<LoggedExercise>? = mutableListOf()
  private set

  constructor(date: Date, plannedWorkoutId: Int, id: Int, finished: Boolean, loggedSet: List<LoggedExercise>) : this(date, plannedWorkoutId) {
    this.id = id
    this.finished = finished
    this.loggedSets = loggedSets
  }
}