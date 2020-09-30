package vzijden.workout.data.mapper

import vzijden.workout.data.views.LoggedWorkoutWithSets
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout

fun mapToEntity(loggedWorkoutWithSets: LoggedWorkoutWithSets): LoggedWorkout {
  val exercises = loggedWorkoutWithSets.loggedSets.groupBy { it.exerciseId }.entries.map { entry ->
    LoggedExercise(entry.key, entry.value)
  }
}