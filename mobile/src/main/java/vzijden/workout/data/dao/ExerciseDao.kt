package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.Exercise

@Dao
interface ExerciseDao {
  @Insert
  fun insert(exercise: Exercise): Long
}