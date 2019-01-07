package vzijden.workout.view.schedule.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_workout_add_item_view.view.*
import vzijden.workout.R
import vzijden.workout.data.model.Exercise
import vzijden.workout.databinding.WorkoutItemViewBinding
import vzijden.workout.view.BindableAdapter
import vzijden.workout.view.schedule.workout.exercise.ExerciseItemViewModel

class EditWorkoutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<Exercise> {
  override fun setData(items: List<Exercise>) {
    exercises = items
  }

  override fun changedPositions(positions: Set<Int>) {
    positions.forEach {
      notifyItemChanged(it)
    }
  }



  private var exercises: List<Exercise>? = null
  private var listener: (() -> Unit)? = null

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
    return if (p1 == 1) {
      val binding: WorkoutItemViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(p0.context),
        R.layout.workout_item_view,
        p0,
        false
      )
      binding.viewModel = ExerciseItemViewModel()
      ExerciseViewHolder(binding)
    } else {
      AddItemViewHolder(
        LayoutInflater.from(p0.context).inflate(R.layout.edit_workout_add_item_view, p0, false) as ViewGroup
      )
    }
  }

  override fun getItemCount(): Int {
    val exerciseCount = exercises?.size ?: 0
    return exerciseCount + 1
  }

  override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    if (p0.itemViewType == 1) {
      (p0 as ExerciseViewHolder).let { exerciseViewHolder ->
        exercises?.get(p1)?.let {
          exerciseViewHolder.workoutItemViewBinding.viewModel?.exerciseName?.set(it.name)
          exerciseViewHolder.workoutItemViewBinding.viewModel?.muscleGroup?.set(it.muscleGroup.normalName)
          exerciseViewHolder.workoutItemViewBinding.viewModel?.index?.set(p1.toString())
        }
      }
    } else (p0 as AddItemViewHolder).let {addItemViewHolder ->
      addItemViewHolder.viewGroup.edit_workout_add_item_view_button.setOnClickListener {
        listener?.invoke()
      }
    }
  }

  override fun getItemViewType(position: Int): Int {
    if (position == exercises?.size ?: 1) {
      return 0
    } else return 1
  }

  fun setOnExerciseAddListener(listener: () -> Unit) {
    this.listener = listener
  }

  inner class ExerciseViewHolder(var workoutItemViewBinding: WorkoutItemViewBinding) : RecyclerView.ViewHolder(workoutItemViewBinding.root)

  inner class AddItemViewHolder(var viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
}