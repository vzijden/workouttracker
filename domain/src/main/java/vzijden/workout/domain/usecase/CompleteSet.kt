package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.repository.WorkoutRepository
import java.lang.RuntimeException

class CompleteSet(val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<CompleteSet.Params>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: Params): Completable {
    val currentWorkoutId = workoutRepository.getCurrentWorkoutId() ?: throw RuntimeException("No active workout")

    return workoutRepository.getCurrentLoggedSet().flatMapCompletable { loggedSet ->
      loggedSet.reps = params.reps
      loggedSet.weight = params.weight
      loggedSet.completed = true
      workoutRepository.updateLoggedSet(loggedSet, currentWorkoutId)
    }
  }

  data class Params(val weight: Int, val reps: Int)
}