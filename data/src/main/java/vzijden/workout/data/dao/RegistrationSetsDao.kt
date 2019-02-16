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
  fun getById(setId: Int): Observable<PlannedSetPojo>

  @Update()
  fun update(setPojo: PlannedSetPojo): Completable

  @Delete
  fun delete(plannedSet: PlannedSetPojo): Completable
}