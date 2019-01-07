package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
        @ColumnInfo(name = "name")
        var name: String?,
        var workoutId: Int,
        var muscleGroup: MuscleGroup
) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}