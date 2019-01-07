package vzijden.workout.view.schedule.workout


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.edit_workout.*
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.WorkoutAndExercices
import vzijden.workout.databinding.EditWorkoutBinding

class EditWorkoutActivity : AppCompatActivity(), EditWorkoutPresenter.View {
  companion object {

    private const val WORKOUT_ID = "WORKOUT_ID"
    fun createIntent(workoutId: Int): Bundle {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID, workoutId)
      return bundle
    }

  }
  private lateinit var workoutAndExercises: WorkoutAndExercices

  private lateinit var presenter: EditWorkoutPresenter
  private lateinit var adapter: EditWorkoutAdapter
  override fun setWorkoutAndExercises(workoutAndExercises: WorkoutAndExercices) {
//    adapter.setData(workoutAndExercises)
  }


  override fun addExercise(exercise: Exercise) {
//    adapter.addExercise(exercise)
  }

  override fun openExercise(exercise: Exercise) {
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    presenter = EditWorkoutPresenter(ScheduleDatabase.getInstance(this), this)
    val binding: EditWorkoutBinding = DataBindingUtil.setContentView(this, R.layout.edit_workout)
    binding.viewModel = presenter

    setupRecyclerView()

    if (intent.hasExtra(WORKOUT_ID)) {
      presenter.loadWorkout(intent.getIntExtra(WORKOUT_ID, 0))
    } else {
      workoutAndExercises = presenter.newWorkoutAndExercises()
    }

  }

  private fun setupRecyclerView() {
    adapter = EditWorkoutAdapter()
    edit_schedule_recyclerView.apply {
      layoutManager = LinearLayoutManager(this@EditWorkoutActivity)
      adapter = this@EditWorkoutActivity.adapter
    }
    adapter.setOnExerciseAddListener {
      presenter.newExercise()
    }
  }
}