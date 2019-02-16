package vzijden.workout.domain.usecase

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class GetWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                 postExecutionScheduler: Scheduler) :
    ObservableUseCase<PlannedWorkout, Long>(subscribeScheduler, postExecutionScheduler) {


  override fun build(workoutId: Long?): Observable<PlannedWorkout> {
    return workoutRepository.getPlannedWorkout(workoutId!!)
  }
}