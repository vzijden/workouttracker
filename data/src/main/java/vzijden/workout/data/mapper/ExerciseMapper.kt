package vzijden.workout.data.mapper

import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.domain.model.Exercise

fun mapExerciseToPojo(entity: Exercise): ExercisePojo {
  return ExercisePojo(entity.name, entity.description, entity.id, entity.muscleGroups.map(::mapMuscleGroupsToPojo))
}

fun mapExerciseToEntity(pojo: ExercisePojo): Exercise {
  return Exercise(pojo.name, pojo.description, pojo.id, pojo.muscleGroupPojos.orEmpty().map(::mapMuscleGroupToEntity))
}