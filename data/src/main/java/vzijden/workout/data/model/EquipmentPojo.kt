package vzijden.workout.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EquipmentPojo (
    var name: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(name: String, id: Int): this(name) {
        this.id = id
    }
}