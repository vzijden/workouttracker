package vzijden.workout.data.repository

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.mapper.*
import vzijden.workout.domain.model.*
import vzijden.workout.domain.repository.WorkoutRepository

class WorkoutRepositoryImpl(private val workoutDatabase: WorkoutDatabase, context: Context) : WorkoutRepository {
  companion object {
    private const val SHARED_PREF_NAME = "VZIJDEN.SHAREDPREFS"
    private const val PREF_CURRENT_WORKOUT = "CURRENT_WORKOUT"
    private const val PREF_CURRENT_EXERCISE = "CURRENT_EXERCISE"
  }

  private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

  override fun getCurrentExercise(): Observable<LoggedExercise> {
    return if (sharedPreferences.contains(PREF_CURRENT_EXERCISE)) {
      val currentExerciseId = sharedPreferences.getLong(PREF_CURRENT_EXERCISE, 0)
       workoutDatabase.registrationDao().get(currentExerciseId).map { mapLoggedExerciseToEntity(it) }
    } else Observable.empty()
  }

  override fun setCurrentExercise(currentExerciseId: Long) {
    sharedPreferences.edit().putLong(PREF_CURRENT_EXERCISE, currentExerciseId).apply()
  }


  override fun getCurrentWorkout(): Observable<LoggedWorkout> {
    return if (sharedPreferences.contains(PREF_CURRENT_WORKOUT)) {
      val currentWorkoutId = sharedPreferences.getInt(PREF_CURRENT_WORKOUT, 0)
      workoutDatabase.workoutDao().getLoggedWorkout(currentWorkoutId).map { mapLoggedWorkoutToEntity(it) }
    } else Observable.empty()
  }

  override fun setCurrentWorkout(workoutId: Int) {
    sharedPreferences.edit().putInt(PREF_CURRENT_WORKOUT, workoutId).apply()
  }

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

  override fun getPlannedSets(plannedExerciseId: Long): Observable<List<PlannedSet>> {
    return workoutDatabase.setsDao().getAllForPlannedExercise(plannedExerciseId).map {
      it.map { plannedSetPojo -> mapPlannedSetToEntity(plannedSetPojo) }
    }
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

  override fun deletePlannedSet(plannedSetId: Long) {
    workoutDatabase.setsDao().deleteById(plannedSetId)
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

  override fun saveLoggedExercise(loggedExercise: LoggedExercise): Single<Long> {
    return workoutDatabase.registrationDao().insert(mapLoggedExerciseToPojo(loggedExercise))
  }
}
