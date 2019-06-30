package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlannedExercisePojo(
    var workoutId: Long,
    @Embedded(prefix = "exercise_")
    var exercisePojo: ExercisePojo,
    var index: Int
) {
  constructor(workoutId: Long, exercisePojo: ExercisePojo, index: Int, id: Long) : this(workoutId, exercisePojo, index) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}