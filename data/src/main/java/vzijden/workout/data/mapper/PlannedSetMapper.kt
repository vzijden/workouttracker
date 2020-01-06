package vzijden.workout.data.mapper

import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.domain.model.PlannedSet

fun mapPlannedSetToPojo(entity: PlannedSet, plannedExerciseId: Int): PlannedSetPojo {
  return PlannedSetPojo(entity.reps, entity.index, plannedExerciseId, entity.id)
}

fun mapPlannedSetToEntity(pojo: PlannedSetPojo): PlannedSet {
  return PlannedSet(pojo.reps, pojo.index, pojo.id)
}
