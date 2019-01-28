package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.PlannedSetPojo

@Dao
interface RegistrationSetsDao {
  @Insert
  fun insert(plannedSetPojo: PlannedSetPojo): Long

  @Insert
  fun insertLogged(loggedSetPojo: LoggedSetPojo): Long

  @Query("SELECT * FROM PlannedSetPojo WHERE id = :setId")
  fun getById(setId: Int): Flowable<PlannedSetPojo>

  @Update()
  fun update(setPojo: PlannedSetPojo): Completable
}