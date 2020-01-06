package vzijden.workout.domain.repository

import io.reactivex.*
import vzijden.workout.domain.model.*

interface WorkoutRepository {
  fun getAllExercises(): Observable<List<Exercise>>
  fun getExercise(exerciseId: Long): Observable<Exercise>

  fun getCurrentWorkoutId(): Long?
  fun setCurrentWorkoutId(id: Long)
  fun createWorkout(): Single<Long>

  fun createLoggedSet(reps: Int, exerciseId: Long, workoutId: Long): Single<Long>
  fun getSetsForLoggedWorkout(workoutId: Long): Observable<List<LoggedSet>>
}