package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSetPojo(
    var weight: Int,
    var reps: Int,
    var unit: Int,
    var loggedExerciseId: Int
) {
  constructor(weight: Int, reps: Int, loggedExerciseId: Int, unit: Int, id: Int, completed: Boolean) :
      this(weight, reps, loggedExerciseId, unit) {
    this.id = id
    this.completed = completed
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  var completed = false;
}