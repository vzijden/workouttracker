package vzijden.workout.view.schedule.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.R
import vzijden.workout.databinding.WorkoutItemViewBinding
import vzijden.workout.view.AbstractAdapter

class EditWorkoutAdapter : AbstractAdapter<EditWorkoutPresenter.ExerciseItemPresenter>() {
  companion object {
    const val VIEW_HOLDER_TYPE = 1
  }

  var exerciseItemPresenters: List<EditWorkoutPresenter.ExerciseItemPresenter>? = null

  override fun getHolderViewType(): Int = VIEW_HOLDER_TYPE

  override fun setData(items: List<EditWorkoutPresenter.ExerciseItemPresenter>) {
    exerciseItemPresenters = items
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return if (viewType == 1) {
      val binder: WorkoutItemViewBinding = DataBindingUtil.inflate(inflater, R.layout.workout_item_view, parent, false)
      ExerciseViewHolder(binder)
    } else super.onCreateViewHolder(parent, viewType)
  }

  override fun getItemCount(): Int = (exerciseItemPresenters?.size ?: 0) + 1


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder.itemViewType == getHolderViewType()) {
      (holder as ExerciseViewHolder).let { workoutItemViewBinding ->
        exerciseItemPresenters?.getOrNull(position)?.let { exerciseItemView ->
          workoutItemViewBinding.workoutItemViewBinding.exerciseItemPresenter = exerciseItemView
        }
      }
    } else super.onBindViewHolder(holder, position)
  }

  inner class ExerciseViewHolder(val workoutItemViewBinding: WorkoutItemViewBinding) : RecyclerView.ViewHolder(workoutItemViewBinding.root)
}
