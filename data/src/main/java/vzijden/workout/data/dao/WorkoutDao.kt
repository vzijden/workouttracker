package vzijden.workout.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.views.LoggedWorkoutAndRegistrations
import vzijden.workout.data.views.WorkoutAndHistory
import vzijden.workout.data.views.WorkoutAndRegistrations
import vzijden.workout.domain.model.LoggedWorkout

@Dao
interface WorkoutDao {
  @Insert
  fun insert(plannedWorkoutPojo: PlannedWorkoutPojo): Single<Long>

  @Query("SELECT * from PlannedWorkoutPojo where PlannedWorkoutPojo.id = :workoutId")
  fun getById(workoutId: Int): Observable<WorkoutAndRegistrations>

  @Query("SELECT * FROM PlannedWorkoutPojo where PlannedWorkoutPojo.id = :workoutId")
  fun getWorkoutAndHistory(workoutId: Int): Observable<WorkoutAndHistory>

  @Insert
  fun insertLogged(loggedWorkoutPojo: LoggedWorkoutPojo): Single<Long>

  @Query("SELECT * FROM LoggedWorkoutPojo where id = :loggedWorkoutId")
  fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkoutAndRegistrations>

  @Query("SELECT * FROM PlannedWorkoutPojo where scheduleId = :scheduleId")
  fun getAllForSchedule(scheduleId: Int): Observable<List<WorkoutAndRegistrations>>
}