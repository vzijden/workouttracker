package vzijden.workout.view.edit.workout


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import dagger.android.support.DaggerAppCompatActivity

class EditWorkoutActivity : DaggerAppCompatActivity() {
  companion object {
    private const val FRAGMENT_CONTAINER_ID = 1
    private const val WORKOUT_ID = "WORKOUT_ID"
    fun createIntent(workoutId: Int): Bundle {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID, workoutId)
      return bundle
    }
  }

  @SuppressLint("ResourceType")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val frameLayout = FrameLayout(this)
    frameLayout.id = FRAGMENT_CONTAINER_ID

    setContentView(frameLayout, ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    val workoutId = intent.getIntExtra(WORKOUT_ID, -1)
    val exercisesFragment = if (workoutId != -1) {
      EditExercisesFragment.createInstance(workoutId)
    } else {
      EditExercisesFragment()
    }

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().add(FRAGMENT_CONTAINER_ID, exercisesFragment).commit()
    }


  }
}