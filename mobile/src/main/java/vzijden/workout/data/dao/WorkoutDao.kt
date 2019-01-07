package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import vzijden.workout.data.model.Workout

@Dao
interface WorkoutDao {
  @Insert
  fun insert(workout: Workout): Long
}