package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.model.PlannedWorkoutPojo

class WorkoutAndRegistrations {
  @Embedded
  lateinit var plannedWorkoutPojo: PlannedWorkoutPojo
  @Relation(parentColumn = "id", entityColumn = "workoutId", entity = PlannedExercisePojo::class)
  var plannedExercisePojos: MutableList<RegistrationAndSets> = mutableListOf()
}
