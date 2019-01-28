package vzijden.workout.dagger.component

import dagger.Component
import vzijden.workout.dagger.module.DataBaseModule
import vzijden.workout.view.home.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class])
interface AppComponent {
  fun inject(activity: MainActivity)
}