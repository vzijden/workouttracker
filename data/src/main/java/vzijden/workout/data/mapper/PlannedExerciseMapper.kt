package vzijden.workout.data.mapper

import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedSet

fun mapPlannedExerciseToPojo(plannedExercise: PlannedExercise): PlannedExercisePojo {
  return PlannedExercisePojo(plannedExercise.workoutId, mapExerciseToPojo(plannedExercise.exercise), plannedExercise.id)
}

fun mapPlannedExerciseToEntity(plannedExercisePojo: PlannedExercisePojo, plannedSets: List<PlannedSet>): PlannedExercise {
  return PlannedExercise(plannedExercisePojo.workoutId, mapExerciseToEntity(plannedExercisePojo.exercisePojo),
      plannedExercisePojo.id, plannedSets)
}