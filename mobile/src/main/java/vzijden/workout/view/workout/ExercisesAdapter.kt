package vzijden.workout.view.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.adapter.AbstractAddItemAdapter2
import vzijden.workout.databinding.ItemCurrentWorkoutListBinding

class ExercisesAdapter : AbstractAddItemAdapter2<ExerciseItemViewModel>() {
  companion object {
    private const val ITEM_VIEW_TYPE = 1
  }

  override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
    val binding: ItemCurrentWorkoutListBinding = DataBindingUtil.inflate(layoutInflater,
        vzijden.workout.R.layout.item_current_workout_list, parent, false)

    return ViewHolder(binding)
  }

  override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    (holder as ViewHolder).binding.viewModel = list[position]
  }

  override fun getHolderViewType(pos: Int): Int {
    return ITEM_VIEW_TYPE
  }

  inner class ViewHolder(val binding: ItemCurrentWorkoutListBinding) : RecyclerView.ViewHolder(binding.root)

}