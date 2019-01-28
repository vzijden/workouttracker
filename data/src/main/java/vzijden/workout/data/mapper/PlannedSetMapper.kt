package vzijden.workout.data.mapper

import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.domain.model.PlannedSet

fun mapPlannedSetToPojo(entity: PlannedSet): PlannedSetPojo {
  return PlannedSetPojo(entity.reps, entity.registrationId, entity.id)
}

fun mapPlannedSetToEntity(pojo: PlannedSetPojo): PlannedSet {
  return PlannedSet(pojo.reps, pojo.registrationId, pojo.id)
}
