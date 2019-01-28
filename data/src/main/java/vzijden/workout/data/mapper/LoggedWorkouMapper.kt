package vzijden.workout.data.mapper

import vzijden.workout.data.views.LoggedWorkoutAndRegistrations
import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.domain.model.LoggedWorkout

fun mapLoggedWorkoutToPojo(loggedWorkout: LoggedWorkout): LoggedWorkoutPojo {
  return LoggedWorkoutPojo(loggedWorkout.date, loggedWorkout.plannedWorkoutId, loggedWorkout.id, loggedWorkout.finished)
}

fun mapLoggedWorkoutToEntity(loggedWorkoutAndRegistrations: LoggedWorkoutAndRegistrations): LoggedWorkout {
  return LoggedWorkout(loggedWorkoutAndRegistrations.loggedWorkoutPojo.date,
      loggedWorkoutAndRegistrations.loggedWorkoutPojo.plannedWorkoutId,
      loggedWorkoutAndRegistrations.loggedWorkoutPojo.id,
      loggedWorkoutAndRegistrations.loggedWorkoutPojo.finished,
      loggedWorkoutAndRegistrations.loggedExercises.map { mapLoggedExerciseToEntity(it) }
  )
}