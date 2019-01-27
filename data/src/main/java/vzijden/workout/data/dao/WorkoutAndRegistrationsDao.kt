package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Query
import vzijden.workout.data.model.WorkoutAndRegistrations

@Dao
interface WorkoutAndRegistrationsDao {
//  @Query("SELECT * from Workout")
//  fun allWorkoutsAndExercises(): List<WorkoutAndRegistrations>

  @Query("SELECT * from Workout where Workout.id = :workoutId")
  fun byWorkoutId(workoutId: Int) : WorkoutAndRegistrations?
}