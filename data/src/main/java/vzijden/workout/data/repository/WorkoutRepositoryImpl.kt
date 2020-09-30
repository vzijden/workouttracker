package vzijden.workout.data.repository

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.mapper.*
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.domain.model.*
import vzijden.workout.domain.repository.WorkoutRepository
import java.lang.RuntimeException
import java.util.Date

class WorkoutRepositoryImpl(private val workoutDatabase: WorkoutDatabase, context: Context) : WorkoutRepository {
  companion object {
    private const val SHARED_PREF_NAME = "VZIJDEN.SHAREDPREFS"
    private const val PREF_CURRENT_WORKOUT = "CURRENT_WORKOUT"
    private const val PREF_CURRENT_EXERCISE = "CURRENT_EXERCISE"
  }

  private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

  override fun getAllExercises(): Observable<List<Exercise>> {
    return workoutDatabase.exerciseDao().getAll().map {
      it.map { mapExerciseToEntity(it) }
    }
  }

  override fun getExercise(exerciseId: Long): Observable<Exercise> {
    return workoutDatabase.exerciseDao().get(exerciseId).map { mapExerciseToEntity(it) }
  }

  override fun getCurrentWorkoutId(): Long? {
    if (sharedPreferences.contains(PREF_CURRENT_WORKOUT)) {
      return sharedPreferences.getLong(PREF_CURRENT_WORKOUT, -1)
    }

    return null
  }

  override fun createWorkout(): Single<Long> {
    return workoutDatabase.registrationDao().insertWorkout(LoggedWorkoutPojo(Date()))
  }

  override fun setCurrentWorkoutId(id: Long) {
    sharedPreferences.edit().putLong(PREF_CURRENT_WORKOUT, id).apply()
  }

  override fun createLoggedSet(reps: Int, exerciseId: Long, workoutId: Long): Single<Long> {
    return workoutDatabase.registrationDao().countLoggedSetsForWorkout(workoutId).flatMap { count ->
       workoutDatabase.registrationDao().insertLoggedSet(LoggedSetPojo(reps, exerciseId, count + 1, workoutId))
    }
  }

  override fun deleteLoggedSet(loggedSetId: Long): Completable {
    return workoutDatabase.registrationDao().deleteLoggedSet(loggedSetId)
  }

  override fun getSetsForLoggedWorkout(workoutId: Long): Observable<List<LoggedSet>> {
    return workoutDatabase.registrationDao().getLoggedSetsForWorkout(workoutId).map { loggedSetPojos ->
      loggedSetPojos.map { loggedSetPojo ->
        val exercise = workoutDatabase.exerciseDao().get(loggedSetPojo.exerciseId).blockingFirst()
        mapLoggedSetToEntity(loggedSetPojo, exercise)
      }
    }
  }

  override fun getCurrentLoggedSet(): Single<LoggedSet> {
    val workoutId = getCurrentWorkoutId() ?: throw RuntimeException("No current workout")

    return workoutDatabase.registrationDao().getCurrentLoggedSet(workoutId).flatMap { loggedSetPojo ->
      workoutDatabase.exerciseDao().get(loggedSetPojo.exerciseId).map { exercise ->
        mapLoggedSetToEntity(loggedSetPojo, exercise)
      }.singleOrError()
    }
  }

  override fun updateLoggedSet(loggedSet: LoggedSet, workoutId: Long): Completable {
    return workoutDatabase.registrationDao().update(mapLoggedSetToPojo(loggedSet, workoutId))
  }
}
