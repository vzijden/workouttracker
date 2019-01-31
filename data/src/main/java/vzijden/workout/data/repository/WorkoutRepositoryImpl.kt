package vzijden.workout.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.mapper.*
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import kotlin.math.log

class WorkoutRepositoryImpl(private val workoutDatabase: WorkoutDatabase): WorkoutRepository {
  override fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout> {
    return workoutDatabase.workoutDao().getById(workoutId).map { mapPlannedWorkoutToEntity(it) }
  }

  override fun createWorkout(plannedWorkout: PlannedWorkout): Single<Long> {
    return workoutDatabase.workoutDao().insert(mapPlannedWorkoutToPojo(plannedWorkout))
  }

  override fun createPlannedSet(plannedSet: PlannedSet): Single<Long> {
    return workoutDatabase.setsDao().insert(mapPlannedSetToPojo(plannedSet))
  }

  override fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Long> {
    return workoutDatabase.workoutDao().insertLogged(mapLoggedWorkoutToPojo(loggedWorkout))
  }

  override fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout> {
    return workoutDatabase.workoutDao().getLoggedWorkout(loggedWorkoutId).map {
      mapLoggedWorkoutToEntity(it)
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
}