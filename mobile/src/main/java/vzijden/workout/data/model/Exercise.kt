package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Exercise(
        @ColumnInfo(name = "name")
        var name: String?,
        var description: String?
) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
        var muscleGroups: List<MuscleGroup>? = null
}