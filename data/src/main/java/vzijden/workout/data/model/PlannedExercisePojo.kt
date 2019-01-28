package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlannedExercisePojo(
    var workoutId: Int,
    @Embedded(prefix = "exercise_")
    var exercisePojo: ExercisePojo
) {
  constructor(workoutId: Int, exercisePojo: ExercisePojo, id: Int) : this(workoutId, exercisePojo) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}