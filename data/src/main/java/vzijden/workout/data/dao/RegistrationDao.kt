package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.model.PlannedExercisePojo

@Dao
interface RegistrationDao {
    @Insert
    fun insert(plannedExercisePojo: PlannedExercisePojo): Single<Long>

    @Delete
    fun delete(plannedExercisePojo: PlannedExercisePojo) : Completable

    @Query("SELECT * FROM PlannedExercisePojo WHERE id = :plannedExerciseId")
    fun get(plannedExerciseId: Int) : Observable<PlannedExercisePojo>
}