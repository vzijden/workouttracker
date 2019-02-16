package vzijden.workout.view.edit.workout.exercise

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import vzijden.workout.data.repository.WorkoutRepositoryImpl
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.CreatePlannedSet
import vzijden.workout.domain.usecase.DeletePlannedSet
import vzijden.workout.scheduler.Schedulers

@Module
abstract class ExerciseItemModule {
  @ContributesAndroidInjector
  abstract fun contributesExerciseItemView(): ExerciseItemView

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideCreatePlannedSet(workoutRepository: WorkoutRepository, schedulers: Schedulers): CreatePlannedSet = CreatePlannedSet(workoutRepository,
        schedulers.subscribeOn(), schedulers.observeOn())

    @Provides
    @JvmStatic
    fun provideDeletePlannedSet(workoutRepository: WorkoutRepository, schedulers: Schedulers): DeletePlannedSet = DeletePlannedSet(workoutRepository,
        schedulers.subscribeOn(), schedulers.observeOn())
  }
}