package vzijden.workout.data.mapper

import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.views.WorkoutAndHistory
import vzijden.workout.domain.model.PlannedWorkout

fun mapPlannedWorkoutToPojo(plannedWorkout: PlannedWorkout): PlannedWorkoutPojo {
  return PlannedWorkoutPojo(plannedWorkout.scheduleId, plannedWorkout.name, plannedWorkout.day, plannedWorkout.id)
}

fun mapPlannedWorkoutToEntity(workoutAndHistory: WorkoutAndHistory): PlannedWorkout {
  return PlannedWorkout(workoutAndHistory.plannedWorkoutPojo.scheduleId, workoutAndHistory.plannedWorkoutPojo.name, workoutAndHistory.plannedWorkoutPojo.day,
      workoutAndHistory.registrationsAndSets.map { mapPlannedExerciseToEntity(it.plannedExercisePojo, it.plannedSetPojos.map(::mapPlannedSetToEntity)) },
      //todo
      emptyList(),
      workoutAndHistory.plannedWorkoutPojo.id)
}