package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSet(
    @Embedded(prefix = "set_")
    val set: Set,
    var weight: Int,
    var loggedWorkoutId: Int
) {
    var unit: Int = 0
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}