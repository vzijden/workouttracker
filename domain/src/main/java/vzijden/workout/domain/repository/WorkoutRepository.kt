package vzijden.workout.domain.repository

import vzijden.workout.domain.model.PlannedWorkout
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedSet

interface WorkoutRepository {
  fun getPlannedWorkout(workoutId: Int): Observable<PlannedWorkout>
  fun createWorkout(plannedWorkout: PlannedWorkout): Single<Long>
  fun createPlannedSet(plannedSet: PlannedSet): Single<Long>
  fun saveLoggedWorkout(loggedWorkout: LoggedWorkout): Single<Long>
  fun getLoggedWorkout(loggedWorkoutId: Int): Observable<LoggedWorkout>
  fun logSet(loggedSet: LoggedSet): Single<Long>
}