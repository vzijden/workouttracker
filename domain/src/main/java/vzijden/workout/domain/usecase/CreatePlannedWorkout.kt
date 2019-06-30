package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedWorkout(private val workoutRepository: WorkoutRepository,
                           subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler):
    SingleUseCase<Long, PlannedWorkout>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: PlannedWorkout): Single<Long> {
    return workoutRepository.createWorkout(params)
  }

}