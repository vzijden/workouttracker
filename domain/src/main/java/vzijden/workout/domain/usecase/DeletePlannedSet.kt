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
    CompletableUseCase<Long>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: Long?): Completable {
    return Completable.fromCallable { workoutRepository.deletePlannedSet(params!!) }
  }

}