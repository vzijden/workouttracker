package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.Schedule

@Dao
interface ScheduleDao {
    @Insert
    fun insert(schedule: Schedule): Long

    @Query("SELECT * from Schedule")
    fun getAll(): List<Schedule>
}