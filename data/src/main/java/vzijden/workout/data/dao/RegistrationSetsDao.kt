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
  fun insert(plannedSetPojo: PlannedSetPojo): Single<Long>

  @Insert
  fun insertLogged(loggedSetPojo: LoggedSetPojo): Single<Long>

  @Query("SELECT * FROM PlannedSetPojo WHERE id = :setId")
  fun getById(setId: Long): Observable<PlannedSetPojo>

  @Query("SELECT * FROM PlannedSetPojo WHERE registrationId = :registrationId")
  fun getAllForPlannedExercise(registrationId: Long): Observable<List<PlannedSetPojo>>

//  @Query("SELECT * FROM PlannedSetPojo as 'set' WHERE id = :plannedSetId " +
//      "and set.index > ")
//  fun getSubsquentPlannedSets(plannedSetId: Long): Observable<List<PlannedSetPojo>>

  @Update()
  fun update(setPojo: PlannedSetPojo): Completable

  @Query("DELETE FROM PlannedSetPojo WHERE id = :plannedSetId")
  fun deleteById(plannedSetId: Long)
}