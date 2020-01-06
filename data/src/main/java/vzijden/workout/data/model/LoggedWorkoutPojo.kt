package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
// Mapping constructor
data class LoggedWorkoutPojo(val date: Date) {
  // Room constructor
  constructor(id: Long, date: Date) : this(date) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}