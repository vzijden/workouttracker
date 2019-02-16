package vzijden.workout.domain.repository

import io.reactivex.*
import vzijden.workout.domain.model.*

interface WorkoutRepository {
  fun getPlannedWorkout(workoutId: Long): Observable<PlannedWorkout>
  fun createWorkout(plannedWorkout: PlannedWorkout): Single<Long>
  fun createPlannedSet(plannedSet: PlannedSet): Single<Long>
  fun createPlannedExercise(plannedExercise: PlannedExercise): Single<Long>
  fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Long>
  fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout>
  fun logSet(loggedSet: LoggedSet): Single<Long>
  fun getPlannedWorkouts(scheduleId: Long): Observable<List<PlannedWorkout>>
  fun savePlannedWorkout(plannedWorkout: PlannedWorkout): Single<Long>
  fun deletePlannedSet(plannedSet: PlannedSet): Completable
  fun getPlannedExercise(plannedExerciseId: Int): Observable<PlannedExercise>
  fun deletePlannedExercise(plannedExercise: PlannedExercise): Completable
  fun getAllExercises(): Observable<List<Exercise>>
  fun getExercise(exerciseId: Long): Observable<Exercise>
}