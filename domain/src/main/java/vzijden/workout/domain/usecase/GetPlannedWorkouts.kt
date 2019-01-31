package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class GetPlannedWorkouts(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                         postExecutionScheduler: Scheduler) :
    ObservableUseCase<List<PlannedWorkout>, Long>(subscribeScheduler, postExecutionScheduler) {


  override fun build(scheduleId: Long?): Observable<List<PlannedWorkout>> {
    return workoutRepository.getPlannedWorkouts(scheduleId!!)
  }
}