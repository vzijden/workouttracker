package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.repository.WorkoutRepository

class DeletePlannedSet(private val workoutRepository: WorkoutRepository,
                       subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<PlannedSet>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: PlannedSet?): Completable {
    return workoutRepository.deletePlannedSet(params!!)
  }

}