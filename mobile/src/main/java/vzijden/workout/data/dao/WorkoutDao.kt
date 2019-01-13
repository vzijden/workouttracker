package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.Workout

@Dao
interface WorkoutDao {
  @Insert
  fun insert(workout: Workout): Long

  @Query("SELECT * from Workout where Workout.id = :workoutId")
  fun getById(workoutId: Int): Workout
}