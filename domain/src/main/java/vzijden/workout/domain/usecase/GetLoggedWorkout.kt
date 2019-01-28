package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class GetLoggedWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                       postExecutionScheduler: Scheduler) :
    ObservableUseCase<LoggedWorkout, Int>(subscribeScheduler, postExecutionScheduler) {


  override fun build(workoutId: Int?): Observable<LoggedWorkout> {
    return workoutRepository.getLoggedWorkout(workoutId!!)
  }
}