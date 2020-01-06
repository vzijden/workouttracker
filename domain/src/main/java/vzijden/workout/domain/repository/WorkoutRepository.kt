package vzijden.workout.domain.repository

import io.reactivex.*
import vzijden.workout.domain.model.*

interface WorkoutRepository {
  fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout>
  fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Int>
  fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout>
  fun logSet(loggedSet: LoggedSet): Single<Int>
  fun getPlannedWorkouts(scheduleId: Int): Observable<List<PlannedWorkout>>
  fun getAllExercises(): Observable<List<Exercise>>
  fun getExercise(exerciseId: Int): Observable<Exercise>
  fun getCurrentExercise(): Observable<LoggedExercise>
  fun getCurrentWorkout(): Observable<LoggedWorkout>
  fun setCurrentWorkout(workoutId: Int)
  fun setCurrentExercise(currentExerciseId: Int)


  fun addExerciseToLoggedWorkout(exercise: LoggedExercise, loggedWorkoutId: Int): Single<Int>
  fun addLoggedSetToLoggedExercise(loggedSet: LoggedSet, loggedExerciseId: Int): Single<Int>
}