package vzijden.workout

import android.app.Application


class App : Application() {
    companion object {
      public const val TAG = "Workout"
    }

    override fun onCreate() {
        super.onCreate()

    }
}