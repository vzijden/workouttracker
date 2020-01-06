package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedSet(private val workoutRepository: WorkoutRepository,
                       subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<PlannedSet, Int>(subscribeScheduler, postExecutionScheduler) {

  public override fun build(registrationId: Int): Single<PlannedSet> {
    return workoutRepository.getPlannedSets(registrationId).firstOrError().flatMap { plannedSets ->
      val index = plannedSets.size
      val plannedSet = PlannedSet(8, index, registrationId)
      workoutRepository.createPlannedSet(plannedSet).map { plannedSetId ->
        PlannedSet(8, index, plannedSetId)
      }
    }
  }

}