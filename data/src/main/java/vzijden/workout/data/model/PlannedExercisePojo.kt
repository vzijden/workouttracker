package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlannedExercisePojo(
    var workoutId: Long,
    @Embedded(prefix = "exercise_")
    var exercisePojo: ExercisePojo
) {
  constructor(workoutId: Long, exercisePojo: ExercisePojo, id: Long) : this(workoutId, exercisePojo) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}