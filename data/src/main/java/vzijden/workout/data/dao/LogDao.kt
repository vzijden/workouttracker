package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.LoggedWorkoutPojo

@Dao
interface LogDao {
    @Insert
    fun insertLoggedSet(loggedSet: LoggedSetPojo): Single<Long>

    @Insert
    fun insertWorkout(workoutPojo: LoggedWorkoutPojo): Single<Long>

    @Query("SELECT * FROM LoggedSetPojo WHERE workoutId = :workoutId")
    fun getLoggedSetsForWorkout(workoutId: Long): Observable<List<LoggedSetPojo>>

    @Query("SELECT COUNT(*) FROM LoggedSetPojo WHERE workoutId = :workoutId")
    fun countLoggedSetsForWorkout(workoutId: Long): Single<Int>
}