package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlannedSetPojo(
    @ColumnInfo(name = "reps")
    var reps: Int,
    var registrationId: Int
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  constructor(reps: Int, registrationId: Int, id: Int) : this(reps, registrationId) {
    this.id = id
  }
}
