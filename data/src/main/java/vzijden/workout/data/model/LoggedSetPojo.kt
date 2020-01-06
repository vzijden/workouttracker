package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import vzijden.workout.domain.model.Exercise

@Entity
// Mapping constructor
data class LoggedSetPojo(
    val reps: Int,
    val exerciseId: Long,
    val index: Int,
    var workoutId: Long
) {
  // Room constructor
  constructor(id: Long, reps: Int, weight: Int, index: Int, loggedExerciseId: Long, completed: Boolean, workoutId: Long) :
      this(reps, loggedExerciseId, index, workoutId) {
    this.id = id
    this.completed = completed
    this.weight = weight
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0

  var completed = false

  var weight = 0
}