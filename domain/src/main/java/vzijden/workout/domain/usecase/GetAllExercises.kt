package vzijden.workout.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.NoParamSingleUseCase
import vzijden.workout.domain.ObservableUseCase
import vzijden.workout.domain.model.Exercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.repository.WorkoutRepository

class GetAllExercises(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler,
                      postExecutionScheduler: Scheduler) :
    NoParamSingleUseCase<List<Exercise>>(subscribeScheduler, postExecutionScheduler) {


  override fun build(): Single<List<Exercise>> {
    return workoutRepository.getAllExercises().firstOrError()
  }
}