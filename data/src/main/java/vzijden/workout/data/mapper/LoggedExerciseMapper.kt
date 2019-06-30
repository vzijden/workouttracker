package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.views.LoggedExerciseAndLoggedSets
import vzijden.workout.domain.model.LoggedExercise


fun mapLoggedExerciseToPojo(loggedExercise: LoggedExercise): LoggedExercisePojo {
  return LoggedExercisePojo(mapPlannedExerciseToPojo(loggedExercise.plannedExercise),
      loggedExercise.loggedWorkoutId.toInt(), loggedExercise.id.toLong())
}

fun mapLoggedExerciseToEntity(loggedExerciseAndLoggedSets: LoggedExerciseAndLoggedSets): LoggedExercise {
  return LoggedExercise(mapPlannedExerciseToEntity(loggedExerciseAndLoggedSets.loggedExercisePojo.plannedExercise,
      loggedExerciseAndLoggedSets.plannedSetPojos.map(::mapPlannedSetToEntity)),
      loggedExerciseAndLoggedSets.loggedSetPojos.map(::mapLoggedSetToEntity),
      loggedExerciseAndLoggedSets.loggedExercisePojo.loggedWorkoutId.toLong())
}