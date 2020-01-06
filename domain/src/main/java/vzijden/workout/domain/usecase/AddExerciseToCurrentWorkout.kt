package vzijden.workout.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import vzijden.workout.domain.CompletableUseCase
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.repository.WorkoutRepository

class AddExerciseToCurrentWorkout(private val workoutRepository: WorkoutRepository,
                                  subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    CompletableUseCase<Int>(subscribeScheduler, postExecutionScheduler) {


  public override fun build(exerciseId: Int): Completable {
    return workoutRepository.getCurrentWorkout().flatMapCompletable { currentWorkout ->
      workoutRepository.addExerciseToLoggedWorkout(LoggedExercise(exerciseId), currentWorkout.id)
          .map { loggedExerciseId ->
            // Default number of sets.. 3?
            (0..3).map { i ->
              // Default number of repetitions.. 8?
              workoutRepository.addLoggedSetToLoggedExercise(LoggedSet(8, i), loggedExerciseId).blockingGet()
            }
          }.ignoreElement()
    }
  }


}