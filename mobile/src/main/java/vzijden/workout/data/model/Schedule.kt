package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
  @ColumnInfo(name = "name") var name: String?
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}