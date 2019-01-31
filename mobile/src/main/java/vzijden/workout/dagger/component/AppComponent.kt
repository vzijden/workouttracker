package vzijden.workout.dagger.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import vzijden.workout.App
import vzijden.workout.dagger.module.ActivityBuilder
import vzijden.workout.dagger.module.AppModule
import vzijden.workout.dagger.module.DataBaseModule
import vzijden.workout.dagger.module.SchedulerModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DataBaseModule::class, ActivityBuilder::class,
  SchedulerModule::class])
interface AppComponent : AndroidInjector<App> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>()
}