package vzijden.workout.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Set(
        @ColumnInfo(name = "reps")
        var reps: Int,
        var registrationId: Int
) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}