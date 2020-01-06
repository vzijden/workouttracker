package vzijden.workout.view.workout

import dagger.Module
import dagger.Provides
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.AddLoggedSetToCurrentWorkout
import vzijden.workout.domain.usecase.GetOrCreateCurrentWorkout
import vzijden.workout.scheduler.Schedulers

@Module
class CurrentWorkoutModule {
  @Provides
  fun providesGetOrCreateCurrentWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers) =
      GetOrCreateCurrentWorkout(workoutRepository, schedulers.subscribeOn(), schedulers.observeOn())

  @Provides
  fun providesAddLoggedSetToCurrentWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers) =
      AddLoggedSetToCurrentWorkout(workoutRepository, schedulers.subscribeOn(), schedulers.observeOn())
}