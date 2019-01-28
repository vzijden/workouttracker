package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.EquipmentPojo

@Dao
interface EquipmentDao {
    @Insert
    fun insert(equipmentPojo: EquipmentPojo): Long
}