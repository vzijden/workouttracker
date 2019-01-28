package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.domain.model.LoggedSet

fun mapLoggedSetToPojo(entity: LoggedSet): LoggedSetPojo {
  return LoggedSetPojo(mapPlannedSetToPojo(entity.plannedSet), entity.loggedExerciseId, entity.weight, entity.loggedWorkoutId,
      entity.unit)
}

 fun mapLoggedSetToEntity(pojo: LoggedSetPojo): LoggedSet {
   return LoggedSet(mapPlannedSetToEntity(pojo.plannedSetPojo), pojo.loggedExerciseId, pojo.weight, pojo.loggedWorkoutId, pojo.unit,
       pojo.id)
}

