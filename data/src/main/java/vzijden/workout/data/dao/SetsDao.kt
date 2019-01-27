package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.LoggedSet
import vzijden.workout.data.model.Set

@Dao
interface SetsDao {
  @Insert
  fun insert(set: Set): Long

  @Insert
  fun insertLogged(loggedSet: LoggedSet): Long

  @Query("SELECT * FROM Set")
  fun getById(setId: Int): Set
}