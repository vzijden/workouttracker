package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.repository.WorkoutRepository

class AddLoggedSetToCurrentWorkout(val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<Long>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: Long): Completable {
    val currentWorkoutId = workoutRepository.getCurrentWorkoutId() ?: throw RuntimeException("No current workout")


    // Default amount of sets.. 3?
    val completable = Single.merge<Long>((0..2).map { i ->
      workoutRepository.createLoggedSet(8, params, currentWorkoutId)
    })

    return workoutRepository.getSetsForLoggedWorkout(currentWorkoutId).first(emptyList())
        .flatMapCompletable { loggedSets ->
          if (loggedSets.isEmpty()) {
            completable.firstOrError().flatMapCompletable { newLoggedSets ->
              workoutRepository.setCurrentLoggedSet(currentWorkoutId, newLoggedSets)
            }
          } else completable.ignoreElements()
        }
  }
}