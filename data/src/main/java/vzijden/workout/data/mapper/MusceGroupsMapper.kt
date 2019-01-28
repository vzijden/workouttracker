package vzijden.workout.data.mapper

import vzijden.workout.data.model.MuscleGroupPojo
import vzijden.workout.domain.model.MuscleGroup


fun mapMuscleGroupsToPojo(muscleGroup: MuscleGroup): MuscleGroupPojo {
  return MuscleGroupPojo.valueOf(muscleGroup.name)
}

fun mapMuscleGroupToEntity(muscleGroupPojo: MuscleGroupPojo): MuscleGroup {
  return MuscleGroup.valueOf(muscleGroupPojo.name)
}