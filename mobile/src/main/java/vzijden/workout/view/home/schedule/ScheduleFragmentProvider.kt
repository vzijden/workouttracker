package vzijden.workout.view.home.schedule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScheduleFragmentProvider {
  @ContributesAndroidInjector(modules = [ScheduleFragmentModule::class])
  abstract fun bindScheduleFragment(): ScheduleFragment
}