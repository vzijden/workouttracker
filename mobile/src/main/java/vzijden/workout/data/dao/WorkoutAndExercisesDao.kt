package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Query
import vzijden.workout.data.model.WorkoutAndExercices

@Dao
interface WorkoutAndExercisesDao {
  @Query("SELECT * from Workout")
  fun allWorkoutsAndExercises(): List<WorkoutAndExercices>

  @Query("SELECT * from Workout where Workout.id = :workoutId")
  fun byWorkoutId(workoutId: Int) : WorkoutAndExercices?
}