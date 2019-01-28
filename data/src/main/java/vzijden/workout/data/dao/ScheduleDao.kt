package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.SchedulePojo

@Dao
interface ScheduleDao {
    @Insert
    fun insert(schedulePojo: SchedulePojo): Long

    @Query("SELECT * from SchedulePojo")
    fun getAll(): List<SchedulePojo>
}