package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LoggedWorkout(
    @Embedded(prefix = "_workout")
    val workout: Workout,
    val date: Date,
    val finished: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}