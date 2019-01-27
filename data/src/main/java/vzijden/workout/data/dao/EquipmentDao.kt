package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.Equipment

@Dao
interface EquipmentDao {
    @Insert
    fun insert(equipment: Equipment): Long
}