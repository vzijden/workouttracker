package vzijden.workout.view.edit.workout

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.*
import vzijden.workout.scheduler.Schedulers

@Module
abstract class EditExercisesModule {
  @ContributesAndroidInjector()
  abstract fun contributesExercisesFragment(): EditExercisesFragment

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

    @Provides
    @JvmStatic
    fun provideDeletePlannedExercise(workoutRepository: WorkoutRepository, schedulers: Schedulers): DeletePlannedExercise = DeletePlannedExercise(workoutRepository, schedulers.subscribeOn(),
        schedulers.observeOn())

    @Provides
    @JvmStatic
    fun provideCreatePlannedExercise(workoutRepository: WorkoutRepository, schedulers: Schedulers) = CreatePlannedExercise(workoutRepository, schedulers.subscribeOn(),
        schedulers.observeOn())

    @Provides
    @JvmStatic
    fun provideCreatePlannedSet(workoutRepository: WorkoutRepository, schedulers: Schedulers) = CreatePlannedSet(workoutRepository, schedulers.subscribeOn(),

        schedulers.observeOn())

    @Provides
    @JvmStatic
    fun provideDeletePlannedSet(workoutRepository: WorkoutRepository, schedulers: Schedulers) = DeletePlannedSet(workoutRepository, schedulers.subscribeOn(),
        schedulers.observeOn())
  }
}