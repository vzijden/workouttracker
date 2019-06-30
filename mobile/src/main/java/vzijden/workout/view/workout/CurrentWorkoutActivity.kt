package vzijden.workout.view.workout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import vzijden.workout.R
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.usecase.GetCurrentWorkout
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.LogSet
import javax.inject.Inject

class CurrentWorkoutActivity : DaggerAppCompatActivity(), CurrentWorkoutViewModel.Activity {
  companion object {
    private const val PLANNED_WORKOUT_ID = "PLANNED_WORKOUT_ID"
    private const val CURRENT_EXERCISES_TAG = "CURRENT_EXERCISES_FRAGMENT_TAG"

    fun createBundle(plannedWorkoutId: Long): Bundle {
      return Bundle().apply {
        putLong(PLANNED_WORKOUT_ID, plannedWorkoutId)
      }
    }
  }

  @Inject
  lateinit var getCurrentWorkout: GetCurrentWorkout
  @Inject
  lateinit var getWorkout: GetWorkout
  @Inject
  lateinit var logSet: LogSet

  private lateinit var currentWorkoutViewModel: CurrentWorkoutViewModel
  private var showingExercisesList = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    currentWorkoutViewModel = CurrentWorkoutViewModel(getCurrentWorkout, getWorkout, logSet)
    DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_current_workout)
    val currentWorkoutId = intent.getLongExtra(PLANNED_WORKOUT_ID, -1)
    currentWorkoutViewModel.bind(this)
    if (currentWorkoutId >= 0) {
      currentWorkoutViewModel.load(currentWorkoutId)
    }
  }

  override fun showExercisesList() {
    val currentExercisesFragment = CurrentExercisesFragment()
    currentExercisesFragment.viewModel = currentWorkoutViewModel
    supportFragmentManager.beginTransaction()
        .replace(R.id.activity_current_workout_container, currentExercisesFragment, CURRENT_EXERCISES_TAG)
        .commit()
    showingExercisesList = true
  }

  override fun showExercise(loggedExercise: LoggedExercise) {

  }

  override fun isShowingExercisesList() = showingExercisesList

}
