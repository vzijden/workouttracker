package vzijden.workout.view.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_current_workout.*
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.databinding.ActivityCurrentWorkoutBinding
import vzijden.workout.view.AbstractAdapter

class CurrentWorkoutActivity : AppCompatActivity(), CurrentWorkoutPresenter.View {
  companion object {
    private const val WORKOUT_ID_ARGUMENT = "WORKOUT_ID"
    fun createBundle(workoutId: Int): Bundle {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID_ARGUMENT, workoutId)
      return bundle
    }
  }

  lateinit var currentWorkoutPresenter: CurrentWorkoutPresenter
  private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    currentWorkoutPresenter = CurrentWorkoutPresenter(this)
    val workoutId = intent.getIntExtra(WORKOUT_ID_ARGUMENT, -1)
    if (workoutId != -1) {
      currentWorkoutPresenter.loadWorkout(workoutId)
    }

    val binding: ActivityCurrentWorkoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_current_workout)
    binding.currentWorkoutPresenter = currentWorkoutPresenter

    activity_current_workout_recyclerview.apply {
      layoutManager = LinearLayoutManager(this@CurrentWorkoutActivity)
      adapter = CurrentWorkoutAdapter()
    }

    bottomSheetBehavior = BottomSheetBehavior.from(activity_current_workout_set_sheet)
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

  }

  override fun hideRepsView() {
    activity_current_workout_coordinator_layout.bottom
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
  }

  override fun showRepsView() {
    activity_current_workout_coordinator_layout.bottom
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
  }


  override fun getDatabase(): ScheduleDatabase {
    return ScheduleDatabase.getInstance(this)
  }

  inner class CurrentWorkoutAdapter : AbstractAdapter<CurrentWorkoutPresenter.CurrentExercisePresenter>() {
    override fun getHolderViewType(): Int = 1

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val currentExerciseView = CurrentExerciseView(parent.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
      }
      return CurrentExerciseViewHolder(currentExerciseView)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      (holder as CurrentExerciseViewHolder).currentExerciseView.setRegistrationAndSets(observableList[position])
    }
  }

  inner class CurrentExerciseViewHolder(val currentExerciseView: CurrentExerciseView) : RecyclerView.ViewHolder(currentExerciseView)

}
