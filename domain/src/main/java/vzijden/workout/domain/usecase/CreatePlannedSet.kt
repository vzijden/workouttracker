package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedSet(private val workoutRepository: WorkoutRepository,
                       subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler):
    SingleUseCase<Long, PlannedSet>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: PlannedSet?): Single<Long> {
    return workoutRepository.createPlannedSet(params!!)
  }

}