package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.repository.WorkoutRepository

class GetPlannedExercise(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                         postExecutionScheduler: Scheduler) :
    ObservableUseCase<PlannedExercise, Int>(subscribeScheduler, postExecutionScheduler) {


  override fun build(workoutId: Int?): Observable<PlannedExercise> {
    return workoutRepository.getPlannedExercise(workoutId!!)
  }
}