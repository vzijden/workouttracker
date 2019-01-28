package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedSet(private val workoutRepository: WorkoutRepository,
                       subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler):
    ObservableUseCase<Long, PlannedSet>(subscribeScheduler, postExecutionScheduler) {

  override fun buildUseObservable(params: PlannedSet?): Observable<Long> {
    return workoutRepository.createPlannedSet(params!!)
  }

}