package vzijden.workout.view.edit.workout.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.adapter.*

class ExercisesAdapter : AbstractAddItemAdapter<ExerciseItemViewModel>(), ItemDeleteAdapter<ExerciseItemViewModel> {
  companion object {
    const val VIEW_HOLDER_TYPE = 1
  }

  private var onItemDeletedListener: OnItemDeletedListener<ExerciseItemViewModel>? = null

  override fun getHolderViewType(pos: Int): Int = VIEW_HOLDER_TYPE

  override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
    val exerciseItemView = ExerciseItemView(parent.context)
    val layoutParams =  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    exerciseItemView.layoutParams = layoutParams
    return ExerciseViewHolder(exerciseItemView)
  }

  override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    (holder as ExerciseViewHolder).let { exerciseViewHolder ->
      observableList.getOrNull(position)?.let { exerciseItemViewModel ->
        exerciseViewHolder.exerciseItemView.load(exerciseItemViewModel, position)
      }
    }
  }

  override fun addOnItemDeletedListener(onItemDeletedListener: OnItemDeletedListener<ExerciseItemViewModel>) {
    this.onItemDeletedListener = onItemDeletedListener
  }

  override fun deleteItem(viewHolderPosition: Int) {
    onItemDeletedListener?.onItemDeleted(observableList[viewHolderPosition], viewHolderPosition)
  }

  class ExerciseViewHolder(val exerciseItemView: ExerciseItemView) : RecyclerView.ViewHolder(exerciseItemView)
}
