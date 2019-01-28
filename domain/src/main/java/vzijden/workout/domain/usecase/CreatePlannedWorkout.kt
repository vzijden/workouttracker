package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class CreatePlannedWorkout(private val workoutRepository: WorkoutRepository,
                           subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler):
    ObservableUseCase<Long, PlannedWorkout>(subscribeScheduler, postExecutionScheduler) {

  override fun buildUseObservable(params: PlannedWorkout?): Observable<Long> {
    return workoutRepository.createWorkout(params!!)
  }

}