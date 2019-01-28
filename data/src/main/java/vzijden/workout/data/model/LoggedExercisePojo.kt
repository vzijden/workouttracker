package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedExercisePojo(
    @Embedded(prefix = "plannedExercise_")
    val plannedExercise: PlannedExercisePojo,
    val loggedWorkoutId: Int
) {
  constructor(plannedExercise: PlannedExercisePojo, loggedWorkoutId: Int, id: Long) : this(plannedExercise,
      loggedWorkoutId) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}