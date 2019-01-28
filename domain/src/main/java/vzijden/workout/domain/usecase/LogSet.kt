package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.repository.WorkoutRepository

class LogSet(private var workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<Long, LoggedSet>(subscribeScheduler, postExecutionScheduler) {

  override fun buildUseSingle(loggedSet: LoggedSet?): Single<Long> {
    return workoutRepository.logSet(loggedSet!!)
  }
}