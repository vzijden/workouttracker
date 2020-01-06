package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.repository.WorkoutRepository

class AddLoggedSetToCurrentWorkout(val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<Long>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: Long): Completable {
    val currentWorkoutId = workoutRepository.getCurrentWorkoutId() ?: throw RuntimeException("No current workout")

    return workoutRepository.createLoggedSet(8, params, currentWorkoutId).ignoreElement()
  }
}