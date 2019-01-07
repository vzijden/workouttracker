package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutAndExercices {
  @Embedded
  lateinit var workout: Workout
  @Relation(parentColumn = "id", entityColumn = "workoutId", entity = Exercise::class)
  lateinit var exercices: MutableList<Exercise>
}
