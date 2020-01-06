package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.views.LoggedExerciseAndLoggedSets
import vzijden.workout.domain.model.LoggedExercise


fun mapLoggedExerciseToPojo(loggedExercise: LoggedExercise, loggedWorkoutId: Int): LoggedExercisePojo {
  return LoggedExercisePojo(loggedExercise.exerciseId, loggedWorkoutId)
}

fun mapLoggedExerciseToEntity(loggedExerciseAndLoggedSets: LoggedExerciseAndLoggedSets): LoggedExercise {
  return LoggedExercise(loggedExerciseAndLoggedSets.loggedExercisePojo.id,
                        loggedExerciseAndLoggedSets.loggedSetPojos.map(::mapLoggedSetToEntity),
                        loggedExerciseAndLoggedSets.exercise,
                        loggedExerciseAndLoggedSets.loggedExercisePojo.exerciseId)
}