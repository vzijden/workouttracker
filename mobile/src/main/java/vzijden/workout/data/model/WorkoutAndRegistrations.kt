package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutAndRegistrations {
  @Embedded
  lateinit var workout: Workout
  @Relation(parentColumn = "id", entityColumn = "workoutId", entity = Registration::class)
  var exercises: MutableList<Registration> = mutableListOf()
}
