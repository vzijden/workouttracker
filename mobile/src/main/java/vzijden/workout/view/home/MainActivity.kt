package vzijden.workout.view.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import vzijden.workout.App
import vzijden.workout.R
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.util.initialize
import vzijden.workout.view.workout.CurrentWorkoutActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var workoutDatabase: WorkoutDatabase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    doAsync {
      if (workoutDatabase.exerciseDao().getAll().blockingFirst().isEmpty()) {
        Log.d(App.TAG, "initializing database")
        initialize(workoutDatabase, this@MainActivity.resources.openRawResource(R.raw.exercises))
      }
    }

    startActivity(Intent(ctx, CurrentWorkoutActivity::class.java))
  }
}
