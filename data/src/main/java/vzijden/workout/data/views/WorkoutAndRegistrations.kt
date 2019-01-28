package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutAndRegistrations {
  @Embedded
  lateinit var plannedWorkoutPojo: PlannedWorkoutPojo
  @Relation(parentColumn = "id", entityColumn = "workoutId", entity = PlannedExercisePojo::class)
  var plannedExercisePojos: MutableList<PlannedExercisePojo> = mutableListOf()
}
