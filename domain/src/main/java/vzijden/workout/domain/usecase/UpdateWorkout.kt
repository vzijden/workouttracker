package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class UpdateWorkout(private var workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<Int, PlannedWorkout>(subscribeScheduler, postExecutionScheduler) {

  override fun build(plannedWorkout: PlannedWorkout): Single<Int> {
    return workoutRepository.savePlannedWorkout(plannedWorkout)
  }
}