package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LoggedWorkoutPojo(
    val date: Date,
    val plannedWorkoutId: Int
) {
  constructor(date: Date, plannedWorkoutId: Int, id: Int, finished: Boolean) : this(date, plannedWorkoutId) {
    this.id = id
    this.finished = finished
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
  var finished: Boolean = false
}