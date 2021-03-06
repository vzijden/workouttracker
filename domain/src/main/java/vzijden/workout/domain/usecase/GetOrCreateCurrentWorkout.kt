package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.NoParamObservableUseCase
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class GetOrCreateCurrentWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) : NoParamObservableUseCase<LoggedWorkout>(subscribeScheduler, postExecutionScheduler) {
  override fun build(): Observable<LoggedWorkout> {
    val currentWorkoutId = workoutRepository.getCurrentWorkoutId()
    if (currentWorkoutId == null) {
      return workoutRepository.createWorkout().flatMapObservable { workoutId ->
        workoutRepository.setCurrentWorkoutId(workoutId)
        workoutRepository.getSetsForLoggedWorkout(workoutId)
            .map { sets ->
              val exercises = sets.groupBy { it.exercise }.entries.map { entry ->
                LoggedExercise(entry.key, entry.value)
              }

              LoggedWorkout(workoutId, exercises)
            }
      }
    }

    return workoutRepository.getSetsForLoggedWorkout(currentWorkoutId)
        .map { sets ->
          val exercises = sets.groupBy { it.exercise }.entries.map { entry ->
            LoggedExercise(entry.key, entry.value)
          }
          LoggedWorkout(currentWorkoutId, exercises)
        }
  }
}