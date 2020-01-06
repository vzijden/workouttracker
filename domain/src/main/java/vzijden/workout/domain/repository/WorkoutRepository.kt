package vzijden.workout.domain.repository

import io.reactivex.*
import vzijden.workout.domain.model.*

interface WorkoutRepository {
  fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout>
  fun createWorkout(plannedWorkout: PlannedWorkout): Single<Int>
  fun createPlannedSet(plannedSet: PlannedSet): Single<Int>
  fun createPlannedExercise(plannedExercise: PlannedExercise): Single<Int>
  fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Int>
  fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout>
  fun logSet(loggedSet: LoggedSet): Single<Int>
  fun getPlannedWorkouts(scheduleId: Int): Observable<List<PlannedWorkout>>
  fun getPlannedSets(plannedExerciseId: Int): Observable<List<PlannedSet>>
  fun savePlannedWorkout(plannedWorkout: PlannedWorkout): Single<Int>
  fun deletePlannedSet(plannedSetId: Int)
  fun getPlannedExercise(plannedExerciseId: Int): Observable<PlannedExercise>
  fun deletePlannedExercise(plannedExercise: PlannedExercise): Completable
  fun getAllExercises(): Observable<List<Exercise>>
  fun getExercise(exerciseId: Int): Observable<Exercise>
  fun getCurrentExercise(): Observable<LoggedExercise>
  fun getCurrentWorkout(): Observable<LoggedWorkout>
  fun setCurrentWorkout(workoutId: Int)
  fun setCurrentExercise(currentExerciseId: Int)
  fun saveLoggedExercise(loggedExercise: LoggedExercise): Single<Int>


  fun addExerciseToLoggedWorkout(exercise: LoggedExercise, loggedWorkoutId: Int): Single<Int>
  fun addLoggedSetToLoggedExercise(loggedSet: LoggedSet, loggedExerciseId: Int): Single<Int>
}