package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedSetPojo(
    @Embedded(prefix = "set_")
    val plannedSetPojo: PlannedSetPojo,
    var weight: Int,
    var loggedWorkoutId: Int,
    var unit: Int
) {
    constructor(plannedSetPojo: PlannedSetPojo, weight: Int, loggedWorkoutId: Int, unit: Int, id: Int):
        this(plannedSetPojo, weight, loggedWorkoutId, unit) {
        this.id = id
    }
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}