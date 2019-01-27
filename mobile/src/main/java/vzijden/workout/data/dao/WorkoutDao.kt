package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import vzijden.workout.data.model.LoggedWorkout
import vzijden.workout.data.model.Workout
import vzijden.workout.data.model.WorkoutAndHistory

@Dao
interface WorkoutDao {
  @Insert
  fun insert(workout: Workout): Long

  @Query("SELECT * from Workout where Workout.id = :workoutId")
  fun getById(workoutId: Int): Workout

  @Query("SELECT * FROM Workout where Workout.id = :workoutId")
  fun getWorkoutAndHistory(workoutId: Int): WorkoutAndHistory

  @Insert
  fun insertLogged(loggedWorkout: LoggedWorkout): Long
}