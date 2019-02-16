package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlannedSetPojo(
    @ColumnInfo(name = "reps")
    var reps: Int,
    var registrationId: Long
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0

  constructor(reps: Int, registrationId: Long, id: Long) : this(reps, registrationId) {
    this.id = id
  }
}
