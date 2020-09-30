package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
// Mapping constructor
data class LoggedWorkoutPojo(val date: Date) {
  // Room constructor
  constructor(id: Long, date: Date, currentLoggedSetId: Long) : this(date) {
    this.id = id
    this.currentLoggedSetId = currentLoggedSetId
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0

  var currentLoggedSetId: Long = -1
}