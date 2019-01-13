package vzijden.workout.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_workout_add_item_view.view.*
import vzijden.workout.R
import vzijden.workout.view.schedule.workout.EditWorkoutPresenter

abstract class AbstractAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<T>, AddItemAdapter {
  companion object {
    const val ADD_ITEM_VIEW_TYPE = 0
  }

  var onAddItemClicked: (() -> Unit)? = null

  final override fun onAddItemClickedListener(listener: () -> Unit) {
    onAddItemClicked = listener
  }

  final override fun changedPositions(positions: Set<Int>) {
    positions.forEach(this::notifyItemChanged)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return AddItemViewHolder(inflater.inflate(R.layout.edit_workout_add_item_view, parent, false) as ViewGroup)

  }


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder.itemViewType == ADD_ITEM_VIEW_TYPE) {
      (holder as AbstractAdapter<*>.AddItemViewHolder).let { addItemViewHolder ->
        addItemViewHolder.viewGroup.edit_workout_add_item_view_button.setOnClickListener {
          onAddItemClicked?.invoke()
        }
      }
    }
  }

  final override fun getItemViewType(position: Int): Int {
    return if (position == itemCount - 1) ADD_ITEM_VIEW_TYPE else getHolderViewType()
  }

  abstract fun getHolderViewType(): Int

  inner class AddItemViewHolder(val viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
}