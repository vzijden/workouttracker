package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Query
import vzijden.workout.data.views.ScheduleAndWorkouts

@Dao
interface ScheduleAndWorkoutsDao {
  @Query("SELECT * from Schedule")
  fun allSchedulesAndWorkouts(): List<ScheduleAndWorkouts>
}