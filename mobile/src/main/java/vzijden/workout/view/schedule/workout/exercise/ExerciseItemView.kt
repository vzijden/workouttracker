package vzijden.workout.view.schedule.workout.exercise

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit_workout_workout_item.view.*
import vzijden.workout.R
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.ExerciseViewSetItemBinding
import vzijden.workout.view.AbstractAdapter

class ExerciseItemView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()

    if (!isInEditMode)
      workout_item_view_sets_list.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = SetsAdapter()
      }
  }

  inner class SetsAdapter : AbstractAdapter<Set>() {
    override fun getHolderViewType(): Int = 1

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val binder: ExerciseViewSetItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.exercise_view_set_item, parent, false)
      return SetViewHolder(binder)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      observableList[position]?.let { exerciseItemPresenter ->
        (holder as SetViewHolder).exerciseViewSetItemBinding.set = exerciseItemPresenter
      }
    }

    inner class SetViewHolder(val exerciseViewSetItemBinding: ExerciseViewSetItemBinding) : RecyclerView.ViewHolder(exerciseViewSetItemBinding.root)
  }
}