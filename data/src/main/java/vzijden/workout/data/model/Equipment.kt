package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipment (
    var name: String? = null
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}