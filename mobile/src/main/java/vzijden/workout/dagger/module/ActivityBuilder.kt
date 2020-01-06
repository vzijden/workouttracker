package vzijden.workout.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vzijden.workout.view.exercises.SelectExerciseActivity
import vzijden.workout.view.exercises.SelectExercisesModule
import vzijden.workout.view.home.HomeModule
import vzijden.workout.view.home.MainActivity
import vzijden.workout.view.workout.CurrentWorkoutActivity
import vzijden.workout.view.workout.CurrentWorkoutModule

@Suppress("unused")
@Module
abstract class ActivityBuilder {
  @ContributesAndroidInjector(modules = [HomeModule::class])
  abstract fun contributeMainActivityInjector(): MainActivity

  @ContributesAndroidInjector(modules = [CurrentWorkoutModule::class])
  abstract fun contributesCurrentWorkoutActivity(): CurrentWorkoutActivity

  @ContributesAndroidInjector(modules = [SelectExercisesModule::class])
  abstract fun contributesSelectExerciseActivity(): SelectExerciseActivity
}