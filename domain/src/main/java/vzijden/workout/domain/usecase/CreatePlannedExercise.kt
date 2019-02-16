package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedExercise(private val workoutRepository: WorkoutRepository,
                            subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<PlannedExercise, CreatePlannedExercise.Params>(subscribeScheduler, postExecutionScheduler) {

  public override fun build(params: Params): Single<PlannedExercise> {
    val (exerciseId, workoutId) = params
    return workoutRepository.getExercise(exerciseId).firstOrError()
        .flatMap { exercise ->
          val plannedExercise = PlannedExercise(workoutId, exercise)
          workoutRepository.createPlannedExercise(plannedExercise).map {plannedExerciseId ->
            PlannedExercise(workoutId, exercise, plannedExerciseId, null)
          }
        }
  }

  data class Params(val exerciseId: Long, val workoutId: Long)

}