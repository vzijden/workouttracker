package vzijden.workout.domain.usecase

import io.reactivex.*
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.repository.WorkoutRepository

class LogSet(private var workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<LoggedSet, LogSet.Params>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: Params): Single<LoggedSet> {
    val (reps, weight) = params
    return workoutRepository.getCurrentExercise().firstOrError().flatMap { currentExercise ->
      val loggedSet = LoggedSet(currentExercise.plannedExercise.exercise, currentExercise.id.toLong(), weight, currentExercise.loggedWorkoutId, reps)

      workoutRepository.logSet(loggedSet).map { loggedSetId ->
        LoggedSet(currentExercise.plannedExercise.exercise, currentExercise.id.toLong(), weight, reps, currentExercise.loggedWorkoutId, 0, loggedSetId)
      }
    }
  }

  data class Params(val reps: Int, val weight: Int)
}