package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlannedSetPojo(
    @ColumnInfo(name = "reps")
    var reps: Int,
    var index: Int,
    var plannedExerciseId: Int
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  constructor(reps: Int, index: Int, plannedExerciseId: Int, id: Int) : this(reps, index, plannedExerciseId) {
    this.id = id
  }
}
