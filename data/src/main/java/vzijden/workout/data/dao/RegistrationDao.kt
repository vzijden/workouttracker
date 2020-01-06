package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.views.LoggedExerciseAndLoggedSets
import vzijden.workout.domain.model.LoggedExercise

@Dao
interface RegistrationDao {
    @Insert
    fun insert(plannedExercisePojo: PlannedExercisePojo): Single<Int>

    @Delete
    fun delete(plannedExercisePojo: PlannedExercisePojo) : Completable

    @Query("SELECT * FROM PlannedExercisePojo WHERE id = :plannedExerciseId")
    fun get(plannedExerciseId: Int) : Observable<PlannedExercisePojo>

    @Query("SELECT * FROM LoggedExercisePojo where id = :loggedExerciseId")
    fun getWithLoggedSets(loggedExerciseId: Int): Observable<LoggedExerciseAndLoggedSets>

    @Insert
    fun insert(loggedExercise: LoggedExercisePojo): Single<Int>
}