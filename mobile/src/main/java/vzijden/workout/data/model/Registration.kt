package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Registration(
    var workoutId: Int
) {
  constructor(workoutId: Int, exercise: Exercise): this(workoutId) {
    this.exercise = exercise
  }

  @Embedded(prefix = "exercise_")
  var exercise: Exercise? = null
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}