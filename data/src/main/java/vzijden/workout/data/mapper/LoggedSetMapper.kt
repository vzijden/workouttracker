package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.views.LoggedExerciseAndLoggedSets
import vzijden.workout.domain.model.LoggedSet

fun mapLoggedSetToPojo(entity: LoggedSet): LoggedSetPojo {
  return LoggedSetPojo(mapExerciseToPojo(entity.exercise), entity.loggedExerciseId, entity.weight, entity.loggedWorkoutId,
      entity.unit)
}

 fun mapLoggedSetToEntity(pojo: LoggedSetPojo): LoggedSet {
   return LoggedSet(mapExerciseToEntity(pojo.exercisePojo), pojo.loggedExerciseId, pojo.weight, pojo.loggedWorkoutId, pojo.unit,
       pojo.id)
}

