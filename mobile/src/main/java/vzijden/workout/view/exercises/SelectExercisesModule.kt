package vzijden.workout.view.exercises

import dagger.Module
import dagger.Provides
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.GetAllExercises
import vzijden.workout.scheduler.Schedulers

@Module
class SelectExercisesModule {

  @Provides
  fun providesGetAllExercises(workoutRepository: WorkoutRepository, schedulers: Schedulers): GetAllExercises = GetAllExercises(workoutRepository, schedulers.subscribeOn(),
      schedulers.observeOn())

}