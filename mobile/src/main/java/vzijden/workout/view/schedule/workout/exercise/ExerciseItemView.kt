package vzijden.workout.view.schedule.workout.exercise

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.workout_item_view.view.*
import vzijden.workout.data.model.Set
import vzijden.workout.R
import vzijden.workout.databinding.ExerciseViewSetItemBinding
import vzijden.workout.view.BindableAdapter

class ExerciseItemView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode)
            workout_item_view_sets_list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = SetsAdapter()
            }
    }

    inner class SetsAdapter : RecyclerView.Adapter<SetsAdapter.SetViewHolder>(), BindableAdapter<Set> {
        var sets: List<Set>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binder: ExerciseViewSetItemBinding = DataBindingUtil.inflate(inflater, R.layout.exercise_view_set_item, parent, false)
            return SetViewHolder(binder)
        }

        override fun getItemCount(): Int = sets?.size ?: 0

        override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
            sets?.get(position)?.let { exerciseItemPresenter ->
                holder.exerciseViewSetItemBinding.set = exerciseItemPresenter
            }
        }

        override fun setData(items: List<Set>) {
            sets = items
            notifyDataSetChanged()
        }

        override fun changedPositions(positions: kotlin.collections.Set<Int>) {
            positions.forEach(this::notifyItemChanged)
        }

        inner class SetViewHolder(val exerciseViewSetItemBinding: ExerciseViewSetItemBinding) : RecyclerView.ViewHolder(exerciseViewSetItemBinding.root)
    }
}