package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSetPojo(
    @Embedded(prefix = "exercise_")
    val exercisePojo: ExercisePojo,
    var weight: Int,
    var loggedWorkoutId: Int,
    var loggedExerciseId: Int,
    var unit: Int
) {
  constructor(exercisePojo: ExercisePojo, weight: Int, loggedWorkoutId: Int, loggedExerciseId: Int, unit: Int, id: Int) :
      this(exercisePojo, weight, loggedWorkoutId, loggedExerciseId, unit) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}