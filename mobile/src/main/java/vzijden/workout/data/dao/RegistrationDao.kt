package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.Registration

@Dao
interface RegistrationDao {
    @Insert
    fun insert(registration: Registration): Long
}