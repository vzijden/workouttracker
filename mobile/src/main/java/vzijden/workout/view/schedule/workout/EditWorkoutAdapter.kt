package vzijden.workout.view.schedule.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.R
import vzijden.workout.databinding.ActivityEditWorkoutWorkoutItemBinding
import vzijden.workout.databinding.ItemDeleteAdapter
import vzijden.workout.databinding.OnItemClickedListener
import vzijden.workout.view.AbstractAdapter

class EditWorkoutAdapter : AbstractAdapter<EditWorkoutPresenter.ExerciseItemPresenter>(), ItemDeleteAdapter<EditWorkoutPresenter.ExerciseItemPresenter> {
  companion object {
    const val VIEW_HOLDER_TYPE = 1
  }

  private var onItemDeletedListener: OnItemClickedListener<EditWorkoutPresenter.ExerciseItemPresenter>? = null

  override fun getHolderViewType(pos: Int): Int = VIEW_HOLDER_TYPE

  override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binder: ActivityEditWorkoutWorkoutItemBinding = DataBindingUtil.inflate(inflater, R.layout.activity_edit_workout_workout_item, parent, false)
    return ExerciseViewHolder(binder)
  }

  override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    (holder as ExerciseViewHolder).let { workoutItemViewBinding ->
      observableList.getOrNull(position)?.let { exerciseItemView ->
        workoutItemViewBinding.workoutItemViewBinding.apply {
          exerciseItemPresenter = exerciseItemView
          index = position + 1
        }
      }

    }
  }

  override fun addOnItemDeletedListener(onItemDeletedListener: OnItemClickedListener<EditWorkoutPresenter.ExerciseItemPresenter>) {
    this.onItemDeletedListener = onItemDeletedListener
  }

  override fun deleteItem(viewHolderPosition: Int) {
    onItemDeletedListener?.onItemClicked(observableList[viewHolderPosition], viewHolderPosition)
  }

  class ExerciseViewHolder(val workoutItemViewBinding: ActivityEditWorkoutWorkoutItemBinding) : RecyclerView.ViewHolder(workoutItemViewBinding.root)
}
