package vzijden.workout.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import javax.inject.Inject

class WorkoutRepositoryImpl(private val workoutDatabase: WorkoutDatabase): WorkoutRepository {
  override fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout> {

  }

  override fun createWorkout(PlannedWorkout: PlannedWorkout): Single<Long> {
  }

  override fun createPlannedSet(plannedSet: PlannedSet): Single<Long> {
  }

  override fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Long> {
  }

  override fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout> {
  }

  override fun logSet(loggedSet: LoggedSet): Single<Long> {
  }
}