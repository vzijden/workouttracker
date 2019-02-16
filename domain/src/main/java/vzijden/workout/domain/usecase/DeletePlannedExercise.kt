package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.repository.WorkoutRepository

class DeletePlannedExercise(private val workoutRepository: WorkoutRepository,
                            subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<PlannedExercise>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: PlannedExercise?): Completable {
    return workoutRepository.deletePlannedExercise(params!!)
  }

}