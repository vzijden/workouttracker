package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import vzijden.workout.data.model.PlannedExercisePojo

@Dao
interface RegistrationDao {
    @Insert
    fun insert(plannedExercisePojo: PlannedExercisePojo): Long

    @Delete
    fun delete(plannedExercisePojo: PlannedExercisePojo)
}