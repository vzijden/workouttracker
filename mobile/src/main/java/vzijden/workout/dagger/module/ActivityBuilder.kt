package vzijden.workout.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vzijden.workout.view.edit.workout.EditWorkoutActivity
import vzijden.workout.view.edit.workout.EditWorkoutModule
import vzijden.workout.view.home.HomeModule
import vzijden.workout.view.home.MainActivity
import vzijden.workout.view.home.schedule.ScheduleFragmentProvider

@Module
abstract class ActivityBuilder {
  @ContributesAndroidInjector(modules = [HomeModule::class, ScheduleFragmentProvider::class])
  abstract fun contributeMainActivityInjector(): MainActivity

  @ContributesAndroidInjector(modules = [EditWorkoutModule::class])
  abstract fun contributeEditWorkoutActivity(): EditWorkoutActivity
}