package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSet(
    @Embedded(prefix = "_set")
    val set: Set,
    var weight: Int
) {
    var unit: Int = 0
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}