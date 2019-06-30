package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSetPojo(
    @Embedded(prefix = "exercise_")
    val exercisePojo: ExercisePojo,
    var weight: Int,
    var reps: Int,
    var loggedWorkoutId: Long,
    var loggedExerciseId: Long,
    var unit: Int
) {
  constructor(exercisePojo: ExercisePojo, weight: Int, reps: Int, loggedWorkoutId: Long, loggedExerciseId: Long, unit: Int, id: Long) :
      this(exercisePojo, weight, reps, loggedWorkoutId, loggedExerciseId, unit) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}