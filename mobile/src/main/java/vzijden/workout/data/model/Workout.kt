package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Workout(
  var date: Date,
  var scheduleId: Int,
  var name: String,
  var day: Int?
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}