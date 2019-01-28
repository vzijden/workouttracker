package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PlannedWorkoutPojo(
  var scheduleId: Int,
  var name: String,
  var day: Int
) {
  constructor(scheduleId: Int, name: String, day: Int, id: Int): this(scheduleId, name, day) {
    this.id = id
  }
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}