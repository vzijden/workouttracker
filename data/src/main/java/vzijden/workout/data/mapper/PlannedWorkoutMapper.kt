package vzijden.workout.data.mapper

import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.views.WorkoutAndRegistrations
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedWorkout

fun mapPlannedWorkoutToPojo(plannedWorkout: PlannedWorkout): PlannedWorkoutPojo {
  return PlannedWorkoutPojo(plannedWorkout.scheduleId, plannedWorkout.name, plannedWorkout.day, plannedWorkout.id)
}

fun mapPlannedWorkoutToEntity(workoutAndRegistrations: WorkoutAndRegistrations): PlannedWorkout {
  return PlannedWorkout(workoutAndRegistrations.plannedWorkoutPojo.scheduleId,
      workoutAndRegistrations.plannedWorkoutPojo.name,
      workoutAndRegistrations.plannedWorkoutPojo.day,
      mapPlannedExercisesList(workoutAndRegistrations),
      workoutAndRegistrations.plannedWorkoutPojo.id)
}

private fun mapPlannedExercisesList(workoutAndRegistrations: WorkoutAndRegistrations): List<PlannedExercise> {
  return workoutAndRegistrations.plannedExercisePojos.map {
    mapPlannedExerciseToEntity(
        it.plannedExercisePojo,
        it.plannedSetPojos.map(::mapPlannedSetToEntity)
    )
  }
}
