package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.ExercisePojo

@Dao
interface ExerciseDao {
  @Insert
  fun insert(exercisePojo: ExercisePojo): Long

  @Query("SELECT * FROM ExercisePojo ORDER BY ExercisePojo.name")
  fun getAll(): List<ExercisePojo>

  @Query("SELECT * from ExercisePojo WHERE ExercisePojo.id = :id")
  fun get(id: Int): ExercisePojo
}