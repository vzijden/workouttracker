package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedExercisePojo(
    val exerciseId: Int,
    val loggedWorkoutId: Int
) {
  constructor(exerciseId: Int, loggedWorkoutId: Int, id: Int) : this(exerciseId, loggedWorkoutId) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}