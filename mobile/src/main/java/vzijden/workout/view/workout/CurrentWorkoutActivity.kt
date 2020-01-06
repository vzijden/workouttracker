package vzijden.workout.view.workout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.ctx
import vzijden.workout.R
import vzijden.workout.domain.usecase.AddLoggedSetToCurrentWorkout
import vzijden.workout.domain.usecase.GetOrCreateCurrentWorkout
import vzijden.workout.view.exercises.SelectExerciseActivity
import javax.inject.Inject

class CurrentWorkoutActivity : DaggerAppCompatActivity(), CurrentWorkoutViewModel.Activity {
  companion object {
    private const val CURRENT_EXERCISES_TAG = "CURRENT_EXERCISES_FRAGMENT_TAG"

    private const val SELECT_EXERCISE_RESULT = 0

  }

  @Inject
  lateinit var getOrCreateCurrentWorkout: GetOrCreateCurrentWorkout
  @Inject
  lateinit var addLoggedSetToCurrentWorkout: AddLoggedSetToCurrentWorkout

  private lateinit var currentWorkoutViewModel: CurrentWorkoutViewModel
  private var showingExercisesList = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    currentWorkoutViewModel = CurrentWorkoutViewModel(getOrCreateCurrentWorkout, addLoggedSetToCurrentWorkout)
    currentWorkoutViewModel.bind(this)
    DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_current_workout)
    currentWorkoutViewModel.load()
  }

  override fun showExercisesList() {
    val currentExercisesFragment = CurrentExercisesFragment()
    currentExercisesFragment.viewModel = currentWorkoutViewModel
    supportFragmentManager.beginTransaction()
        .replace(R.id.activity_current_workout_container, currentExercisesFragment, CURRENT_EXERCISES_TAG)
        .commit()
    showingExercisesList = true
  }

  override fun openSelectExercise() {
    val intent = Intent(ctx, SelectExerciseActivity::class.java)
    startActivityForResult(intent, SELECT_EXERCISE_RESULT)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == SELECT_EXERCISE_RESULT) {
      if (resultCode == Activity.RESULT_OK) {
        data?.let {
          val exerciseId = it.getLongExtra(SelectExerciseActivity.RESULT_ID, -1)
          currentWorkoutViewModel.onAddExerciseSelected(exerciseId)
        }
      }
    }
  }
}
