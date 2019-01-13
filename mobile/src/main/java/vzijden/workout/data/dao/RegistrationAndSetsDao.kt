package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Query
import vzijden.workout.data.model.RegistrationAndSets

@Dao
interface RegistrationAndSetsDao {
  @Query("SELECT * from Registration where Registration.workoutId = :workoutId")
  fun getAllForWorkout(workoutId: Int): MutableList<RegistrationAndSets>

  @Query("SELECT * FROM Registration where Registration.id = :registrationId")
  fun get(registrationId: Int): RegistrationAndSets
}