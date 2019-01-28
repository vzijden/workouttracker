package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.views.WorkoutAndHistory

@Dao
interface WorkoutDao {
  @Insert
  fun insert(plannedWorkoutPojo: PlannedWorkoutPojo): Long

  @Query("SELECT * from PlannedWorkoutPojo where PlannedWorkoutPojo.id = :workoutId")
  fun getById(workoutId: Int): PlannedWorkoutPojo

  @Query("SELECT * FROM PlannedWorkoutPojo where PlannedWorkoutPojo.id = :workoutId")
  fun getWorkoutAndHistory(workoutId: Int): WorkoutAndHistory

  @Insert
  fun insertLogged(loggedWorkoutPojo: LoggedWorkoutPojo): Long
}