package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.LoggedWorkout

fun mapLoggedWorkoutToPojo(loggedWorkout: LoggedWorkout): LoggedWorkoutPojo {
  return LoggedWorkoutPojo(mapPlannedWorkoutToPojo(loggedWorkout.PlannedWorkout), loggedWorkout.date, loggedWorkout.id, loggedWorkout.finished)
}

fun mapLoggedWorkoutToEntity(loggedWorkoutPojo: LoggedWorkoutPojo, loggedSets: List<LoggedSet>): LoggedWorkout {
  return LoggedWorkout(mapPlannedWorkoutToEntity(loggedWorkoutPojo.plannedWorkoutPojo), loggedWorkoutPojo.date, loggedWorkoutPojo.id,
      loggedWorkoutPojo.finished, loggedSets)
}