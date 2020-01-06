package vzijden.workout.data.repository

import android.content.Context
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
      val currentExerciseId = sharedPreferences.getInt(PREF_CURRENT_EXERCISE, 0)
       workoutDatabase.registrationDao().getWithLoggedSets(currentExerciseId).map { mapLoggedExerciseToEntity(it) }
    } else Observable.empty()
  }

  override fun setCurrentExercise(currentExerciseId: Int) {
    sharedPreferences.edit().putInt(PREF_CURRENT_EXERCISE, currentExerciseId).apply()
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

  override fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout> {
    return workoutDatabase.workoutDao().getById(workoutId).map { mapPlannedWorkoutToEntity(it) }
  }

  override fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Int> {
    return workoutDatabase.workoutDao().insertLogged(mapLoggedWorkoutToPojo(loggedWorkout))
  }

  override fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout> {
    return workoutDatabase.workoutDao().getLoggedWorkout(loggedWorkoutId).map {
      mapLoggedWorkoutToEntity(it)
    }
  }

  override fun getExercise(exerciseId: Int): Observable<Exercise> {
    return workoutDatabase.exerciseDao().get(exerciseId).map { mapExerciseToEntity(it) }
  }

  override fun logSet(loggedSet: LoggedSet): Single<Int> {
    TODO()
  }

  override fun getPlannedWorkouts(scheduleId: Int): Observable<List<PlannedWorkout>> {
    return workoutDatabase.workoutDao().getAllForSchedule(scheduleId).map {
      it.map { mapPlannedWorkoutToEntity(it) }
    }
  }

  override fun getAllExercises(): Observable<List<Exercise>> {
    return workoutDatabase.exerciseDao().getAll().map {
      it.map { mapExerciseToEntity(it) }
    }
  }

  override fun addExerciseToLoggedWorkout(exercise: LoggedExercise, loggedWorkoutId: Int): Single<Int> {
    return workoutDatabase.registrationDao().insert(mapLoggedExerciseToPojo(exercise, loggedWorkoutId))
  }

  override fun addLoggedSetToLoggedExercise(loggedSet: LoggedSet, loggedExerciseId: Int): Single<Int> {
    return workoutDatabase.setsDao().insertLogged(mapLoggedSetToPojo(loggedSet, loggedExerciseId))
  }
}
