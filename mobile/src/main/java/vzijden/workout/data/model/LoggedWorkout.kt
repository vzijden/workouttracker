package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LoggedWorkout(
    @Embedded(prefix = "workout_")
    val workout: Workout,
    val date: Date
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var finished: Boolean = false
}