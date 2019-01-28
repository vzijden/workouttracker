package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SchedulePojo(
    @ColumnInfo(name = "name") var name: String
) {
  constructor(name: String, id: Int) : this(name) {
    this.id = id
  }

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}