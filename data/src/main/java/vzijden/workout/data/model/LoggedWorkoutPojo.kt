package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LoggedWorkoutPojo(
    @Embedded(prefix = "workout_")
    val plannedWorkoutPojo: PlannedWorkoutPojo,
    val date: Date
){
    constructor(plannedWorkoutPojo: PlannedWorkoutPojo, date: Date, id: Int, finished: Boolean): this(plannedWorkoutPojo, date) {
        this.id = id
        this.finished = finished
    }
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var finished: Boolean = false
}