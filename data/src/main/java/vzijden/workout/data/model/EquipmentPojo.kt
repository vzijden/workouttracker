package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EquipmentPojo (
    var name: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(name: String, id: Long): this(name) {
        this.id = id
    }
}