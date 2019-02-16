package vzijden.workout.view.edit.workout

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.CreatePlannedExercise
import vzijden.workout.domain.usecase.DeletePlannedExercise
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.UpdateWorkout
import vzijden.workout.scheduler.Schedulers
import vzijden.workout.view.edit.workout.exercise.ExerciseItemModule

@Module
abstract class EditExercisesModule {
  @ContributesAndroidInjector(modules = [ExerciseItemModule::class])
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
  }
}