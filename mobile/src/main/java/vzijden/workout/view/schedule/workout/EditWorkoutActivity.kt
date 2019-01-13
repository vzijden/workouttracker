package vzijden.workout.view.schedule.workout


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise

class EditWorkoutActivity : AppCompatActivity() {
  companion object {
    private const val FRAGMENT_CONTAINER_ID = 1
    private const val WORKOUT_ID = "WORKOUT_ID"
    fun createIntent(workoutId: Int): Bundle {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID, workoutId)
      return bundle
    }
  }

  private lateinit var presenter: EditWorkoutPresenter


  @SuppressLint("ResourceType")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    presenter = EditWorkoutPresenter(ScheduleDatabase.getInstance(this))
    if (intent.hasExtra(WORKOUT_ID)) {
      presenter.loadWorkout(intent.getIntExtra(WORKOUT_ID, 0))
    } else {
      presenter.newWorkoutAndExercises()
    }

    val exercisesFragment = ExercisesFragment();
    exercisesFragment.presenter = presenter

    val frameLayout = FrameLayout(this)
    frameLayout.id = FRAGMENT_CONTAINER_ID

    setContentView(frameLayout, ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().add(FRAGMENT_CONTAINER_ID, exercisesFragment).commit()
      presenter.exercisesFragmentView = exercisesFragment
    }


  }
}