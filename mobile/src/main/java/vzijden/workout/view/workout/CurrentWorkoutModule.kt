package vzijden.workout.view.workout

import dagger.Module
import dagger.Provides
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.GetCurrentWorkout
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.LogSet
import vzijden.workout.scheduler.Schedulers

@Module
class CurrentWorkoutModule {
  @Provides
  fun provideGetWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers) =
      GetWorkout(workoutRepository, schedulers.subscribeOn(), schedulers.observeOn())

  @Provides
  fun provideGetCurrentWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers) =
      GetCurrentWorkout(workoutRepository, schedulers.subscribeOn(), schedulers.observeOn())

  @Provides
  fun provideLogSet(workoutRepository: WorkoutRepository, schedulers: Schedulers) =
      LogSet(workoutRepository, schedulers.subscribeOn(), schedulers.observeOn())
}