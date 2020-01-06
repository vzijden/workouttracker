package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
class PlannedExercisePojo(
    var workoutId: Int,
    @Embedded(prefix = "exercise_")
    var exercisePojo: ExercisePojo,
    var index: Int
) {
  constructor(workoutId: Int, exercisePojo: ExercisePojo, index: Int, id: Int) : this(workoutId, exercisePojo, index) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}