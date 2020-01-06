package vzijden.workout.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import vzijden.workout.data.views.RegistrationAndSets

@Dao
interface RegistrationAndSetsDao {
  @Query("SELECT * from PlannedExercisePojo where PlannedExercisePojo.workoutId = :workoutId")
  fun getAllForWorkout(workoutId: Int): Flowable<RegistrationAndSets>

  @Query("SELECT * FROM PlannedExercisePojo where PlannedExercisePojo.id = :exerciseId")
  fun get(exerciseId: Int): Observable<RegistrationAndSets>
}