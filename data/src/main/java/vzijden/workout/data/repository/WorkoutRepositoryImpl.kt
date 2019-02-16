package vzijden.workout.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.mapper.*
import vzijden.workout.domain.model.*
import vzijden.workout.domain.repository.WorkoutRepository
import kotlin.math.log

class WorkoutRepositoryImpl(private val workoutDatabase: WorkoutDatabase) : WorkoutRepository {
  override fun getPlannedWorkout(workoutId: Long): Observable<PlannedWorkout> {
    return workoutDatabase.workoutDao().getById(workoutId).map { mapPlannedWorkoutToEntity(it) }
  }

  override fun createWorkout(plannedWorkout: PlannedWorkout): Single<Long> {
    return workoutDatabase.workoutDao().insert(mapPlannedWorkoutToPojo(plannedWorkout))
  }

  override fun createPlannedSet(plannedSet: PlannedSet): Single<Long> {
    return workoutDatabase.setsDao().insert(mapPlannedSetToPojo(plannedSet))
  }

  override fun createPlannedExercise(plannedExercise: PlannedExercise): Single<Long> {
    return workoutDatabase.registrationDao().insert(mapPlannedExerciseToPojo(plannedExercise))
  }

  override fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Long> {
    return workoutDatabase.workoutDao().insertLogged(mapLoggedWorkoutToPojo(loggedWorkout))
  }

  override fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout> {
    return workoutDatabase.workoutDao().getLoggedWorkout(loggedWorkoutId).map {
      mapLoggedWorkoutToEntity(it)
    }
  }

  override fun getExercise(exerciseId: Long): Observable<Exercise> {
    return workoutDatabase.exerciseDao().get(exerciseId).map { mapExerciseToEntity(it) }
  }

  override fun logSet(loggedSet: LoggedSet): Single<Long> {
    TODO()
  }

  override fun getPlannedWorkouts(scheduleId: Long): Observable<List<PlannedWorkout>> {
    return workoutDatabase.workoutDao().getAllForSchedule(scheduleId.toInt()).map {
      it.map { mapPlannedWorkoutToEntity(it) }
    }
  }

  override fun savePlannedWorkout(plannedWorkout: PlannedWorkout): Single<Long> {
    return workoutDatabase.workoutDao().insert(mapPlannedWorkoutToPojo(plannedWorkout))
  }

  override fun deletePlannedSet(plannedSet: PlannedSet): Completable {
    return workoutDatabase.setsDao().delete(mapPlannedSetToPojo(plannedSet))
  }

  override fun getPlannedExercise(plannedExerciseId: Int): Observable<PlannedExercise> {
    return workoutDatabase.registrationAndSetsDao().get(plannedExerciseId).map { registrationAndSets ->
      mapPlannedExerciseToEntity(registrationAndSets.plannedExercisePojo,
          registrationAndSets.plannedSetPojos.map { plannedSetPojo ->
            mapPlannedSetToEntity(plannedSetPojo)
          })
    }
  }

  override fun deletePlannedExercise(plannedExercise: PlannedExercise): Completable {
    return workoutDatabase.registrationDao().delete(mapPlannedExerciseToPojo(plannedExercise))
  }

  override fun getAllExercises(): Observable<List<Exercise>> {
    return workoutDatabase.exerciseDao().getAll().map {
      it.map { mapExerciseToEntity(it) }
    }
  }
}
