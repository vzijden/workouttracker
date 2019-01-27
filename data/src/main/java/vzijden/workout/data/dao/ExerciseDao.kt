package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import vzijden.workout.data.model.Exercise

@Dao
interface ExerciseDao {
  @Insert
  fun insert(exercise: Exercise): Long

  @Query("SELECT * FROM Exercise ORDER BY Exercise.name")
  fun getAll(): List<Exercise>

  @Query("SELECT * from Exercise WHERE Exercise.id = :id")
  fun get(id: Int): Exercise
}