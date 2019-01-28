package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSetPojo(
    @Embedded(prefix = "exercise_")
    val plannedExercisePojo: PlannedExercisePojo,
    var weight: Int,
    var loggedWorkoutId: Int,
    var loggedExerciseId: Int,
    var unit: Int
) {
  constructor(plannedExercisePojo: PlannedExercisePojo, weight: Int, loggedWorkoutId: Int, loggedExerciseId: Int, unit: Int, id: Int) :
      this(plannedExercisePojo, weight, loggedWorkoutId, loggedExerciseId, unit) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}