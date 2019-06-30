package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import java.util.*

class CreateLoggedWorkout(private val workoutRepository: WorkoutRepository,
                          subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler):
    ObservableUseCase<LoggedWorkout, PlannedWorkout>(subscribeScheduler, postExecutionScheduler) {

  override fun build(params: PlannedWorkout?): Observable<LoggedWorkout> {
    val loggedWorkout = LoggedWorkout(Date(), params!!.id)

    return workoutRepository.saveLoggedWorkout(loggedWorkout).flatMapObservable {loggedWorkoutId ->
      workoutRepository.getLoggedWorkout(loggedWorkoutId.toInt())
    }
  }

}