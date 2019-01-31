package vzijden.workout.view.edit.workout

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.UpdateWorkout
import vzijden.workout.scheduler.Schedulers

@Module
abstract class EditWorkoutModule {
  @ContributesAndroidInjector
  abstract fun contributesExercisesFragment(): ExercisesFragment

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideGetWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers): GetWorkout = GetWorkout(workoutRepository, schedulers.subscribeOn(),
        schedulers.observeOn())
    @Provides
    @JvmStatic
    fun provideUpdateWorkout(workoutRepository: WorkoutRepository, schedulers: Schedulers): UpdateWorkout = UpdateWorkout(workoutRepository, schedulers.subscribeOn(),
        schedulers.observeOn())
  }
}