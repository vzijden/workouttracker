package vzijden.workout.data.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.domain.model.PlannedSet

@Dao
interface RegistrationSetsDao {
  @Insert
  fun insert(plannedSetPojo: PlannedSetPojo): Single<Int>

  @Insert
  fun insertLogged(loggedSetPojo: LoggedSetPojo): Single<Int>

  @Query("SELECT * FROM PlannedSetPojo WHERE id = :setId")
  fun getById(setId: Int): Observable<PlannedSetPojo>

  @Query("SELECT * FROM PlannedSetPojo WHERE plannedExerciseId = :exerciseId")
  fun getAllForPlannedExercise(exerciseId: Int): Observable<List<PlannedSetPojo>>

//  @Query("SELECT * FROM PlannedSetPojo as 'set' WHERE id = :plannedSetId " +
//      "and set.index > ")
//  fun getSubsquentPlannedSets(plannedSetId: Int): Observable<List<PlannedSetPojo>>

  @Update()
  fun update(setPojo: PlannedSetPojo): Completable

  @Query("DELETE FROM PlannedSetPojo WHERE id = :plannedSetId")
  fun deleteById(plannedSetId: Int)
}