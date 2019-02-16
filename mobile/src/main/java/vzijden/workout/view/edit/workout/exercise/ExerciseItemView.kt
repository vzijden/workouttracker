package vzijden.workout.view.edit.workout.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_edit_exercise.view.*
import vzijden.workout.R
import vzijden.workout.adapter.AbstractAddItemAdapter
import vzijden.workout.databinding.ExerciseViewSetItemBinding
import vzijden.workout.view.edit.workout.exercise.set.SetItemViewModel

class ExerciseItemView(context: Context) : LinearLayout(context) {
  var binding: vzijden.workout.databinding.ItemEditExerciseBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_edit_exercise, this, true)

  fun load(exerciseItemViewModel: ExerciseItemViewModel, index: Int) {
    binding.viewModel = exerciseItemViewModel
    workout_item_view_sets_list.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = SetsAdapter()
    }
  }


  class SetsAdapter : AbstractAddItemAdapter<SetItemViewModel>() {
    override fun getHolderViewType(pos: Int): Int = 1

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val binder: ExerciseViewSetItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.exercise_view_set_item, parent, false)
      return SetViewHolder(binder)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      observableList[position]?.let { setItemViewModel ->
        (holder as SetViewHolder).exerciseViewSetItemBinding.viewModel = setItemViewModel
      }
    }

    inner class SetViewHolder(val exerciseViewSetItemBinding: ExerciseViewSetItemBinding) : RecyclerView.ViewHolder(exerciseViewSetItemBinding.root)
  }
}
