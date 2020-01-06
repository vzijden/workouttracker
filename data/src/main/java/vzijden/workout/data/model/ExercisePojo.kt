package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExercisePojo(
        @ColumnInfo(name = "name")
        var name: String,
        var description: String,
        var muscleGroupPojos: List<MuscleGroupPojo>? = listOf()
) {
        constructor(name: String, description: String, id: Int, muscleGroups: List<MuscleGroupPojo>): this(name, description, muscleGroups) {
                this.id = id
        }

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0

}