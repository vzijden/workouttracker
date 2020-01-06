package vzijden.workout.data.mapper

import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.domain.model.LoggedSet

fun mapLoggedSetToPojo(loggedSet: LoggedSet, workoutId: Long): LoggedSetPojo {
  return LoggedSetPojo(loggedSet.reps, loggedSet.exercise.id, loggedSet.index, workoutId)
}

fun mapLoggedSetToEntity(loggedSetPojo: LoggedSetPojo, exercise: ExercisePojo): LoggedSet {
  return LoggedSet(loggedSetPojo.id, loggedSetPojo.reps, mapExerciseToEntity(exercise), loggedSetPojo.index)
}