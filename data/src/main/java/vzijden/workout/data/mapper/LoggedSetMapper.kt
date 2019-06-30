package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.views.LoggedExerciseAndLoggedSets
import vzijden.workout.domain.model.LoggedSet

fun mapLoggedSetToPojo(entity: LoggedSet): LoggedSetPojo {
  return LoggedSetPojo(mapExerciseToPojo(entity.exercise),entity.weight, entity.reps, entity.loggedWorkoutId, entity.loggedExerciseId,
      entity.unit, entity.id)
}

 fun mapLoggedSetToEntity(pojo: LoggedSetPojo): LoggedSet {
   return LoggedSet(mapExerciseToEntity(pojo.exercisePojo), pojo.loggedExerciseId, pojo.weight, pojo.reps, pojo.loggedWorkoutId, pojo.unit,
       pojo.id)
}

