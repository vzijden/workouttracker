package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.Set

@Dao
interface SetsDao {
  @Insert
  fun insert(set: Set): Long
}