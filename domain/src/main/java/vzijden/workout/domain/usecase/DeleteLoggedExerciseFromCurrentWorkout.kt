package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.repository.WorkoutRepository

class DeleteLoggedExerciseFromCurrentWorkout(val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<LoggedExercise>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: LoggedExercise): Completable {
    return Completable.merge(
        params.loggedSets.map { workoutRepository.deleteLoggedSet(it.id) }
    )
  }
}