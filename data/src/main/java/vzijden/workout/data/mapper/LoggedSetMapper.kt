package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.domain.model.LoggedSet

fun mapToPojo(entity: LoggedSet): LoggedSetPojo {
  return LoggedSetPojo(mapPlannedSetToPojo(entity.plannedSet), entity.weight, entity.loggedWorkoutId,
      entity.unit)
}

 fun mapToEntity(pojo: LoggedSetPojo): LoggedSet {
   return LoggedSet(mapPlannedSetToEntity(pojo.plannedSetPojo), pojo.weight, pojo.loggedWorkoutId, pojo.unit,
       pojo.id)
}

