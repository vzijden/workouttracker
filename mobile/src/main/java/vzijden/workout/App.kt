package vzijden.workout

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import vzijden.workout.dagger.component.DaggerAppComponent


class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    val build = DaggerAppComponent.builder().create(this)
    build.inject(this)
    return build
  }

  companion object {
    const val TAG = "Workout"
  }

  override fun onCreate() {
    super.onCreate()


  }
}