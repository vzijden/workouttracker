package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import java.util.Collections.emptyList
import java.util.Date

class GetCurrentWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                        postExecutionScheduler: Scheduler) :
    ObservableUseCase<LoggedWorkout, Long>(subscribeScheduler, postExecutionScheduler) {


  public override fun build(plannedWorkoutId: Long?): Observable<LoggedWorkout> {
//    return
//    workoutRepository.getCurrentWorkout().switchIfEmpty { observer ->
    return workoutRepository.getPlannedWorkout(plannedWorkoutId!!).flatMap { plannedWorkout ->

      val loggedWorkout = LoggedWorkout(Date(), plannedWorkoutId.toInt())
      val loggedWorkoutId = workoutRepository.saveLoggedWorkout(loggedWorkout).blockingGet()

      plannedWorkout.plannedExercises.forEach { plannedExercise ->
        val loggedExercise = LoggedExercise(plannedExercise, emptyList(), loggedWorkoutId)
        workoutRepository.saveLoggedExercise(loggedExercise).blockingGet()
      }
      workoutRepository.getLoggedWorkout(loggedWorkoutId.toInt())
    }
  }
}