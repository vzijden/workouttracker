package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import java.util.Date

class GetCurrentWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                        postExecutionScheduler: Scheduler) :
    ObservableUseCase<LoggedWorkout, Int>(subscribeScheduler, postExecutionScheduler) {


  public override fun build(plannedWorkoutId: Int): Observable<LoggedWorkout> {
    return workoutRepository.getPlannedWorkout(plannedWorkoutId).flatMap { plannedWorkout ->

      val loggedWorkout = LoggedWorkout(Date(), plannedWorkoutId)
      val loggedWorkoutId = workoutRepository.saveLoggedWorkout(loggedWorkout).blockingGet()

      plannedWorkout.plannedExercises.forEach { plannedExercise ->
        AddExerciseToCurrentWorkout(workoutRepository, subscribeScheduler, postExecutionScheduler)
            .build(plannedExercise.exercise.id)
      }
      workoutRepository.getLoggedWorkout(loggedWorkoutId)
    }
  }
}