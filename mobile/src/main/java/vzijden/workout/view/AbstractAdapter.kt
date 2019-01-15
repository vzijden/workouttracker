package vzijden.workout.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_workout_add_item_view.view.*
import vzijden.workout.R
import vzijden.workout.databinding.AddItemAdapter
import vzijden.workout.databinding.BindableAdapter
import vzijden.workout.databinding.OnItemClickedListener
import java.lang.RuntimeException

abstract class AbstractAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<T>, AddItemAdapter {
  companion object {
    const val ADD_ITEM_VIEW_TYPE = 0
  }

  private var onAddItemClicked: (() -> Unit)? = null
  private var onItemClickedListener: OnItemClickedListener<T>? = null

  final override fun onAddItemClickedListener(listener: () -> Unit) {
    onAddItemClicked = listener
  }

  override fun addOnItemClickedListener(listener: OnItemClickedListener<T>) {
    onItemClickedListener = listener
  }

  final override fun changedPositions(positions: Set<Int>) {
    positions.forEach(this::notifyItemChanged)
  }

  final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return when (viewType) {
      ADD_ITEM_VIEW_TYPE -> AddItemViewHolder(inflater.inflate(R.layout.edit_workout_add_item_view, parent, false) as ViewGroup)
      getHolderViewType() -> createItemViewHolder(inflater, parent)
      else -> throw RuntimeException("Unknown viewType")
    }
  }


  final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when(holder.itemViewType) {
      ADD_ITEM_VIEW_TYPE -> {
        (holder as AbstractAdapter<*>.AddItemViewHolder).let { addItemViewHolder ->
          addItemViewHolder.viewGroup.edit_workout_add_item_view_button.setOnClickListener {
            onAddItemClicked?.invoke()
          }
        }
      }
      getItemViewType(position) -> {
        bindItemViewHolder(holder, position)
        holder.itemView.setOnClickListener {
          onItemClickedListener?.onItemClicked(getItem(position), position)
        }
      }
      else -> throw RuntimeException("Unknown viewType")
    }
  }

  final override fun getItemViewType(position: Int): Int {
    return if (position == itemCount - 1) ADD_ITEM_VIEW_TYPE else getHolderViewType()
  }

  abstract fun getItem(position: Int): T

  abstract fun getHolderViewType(): Int

  abstract fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

  abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)

  inner class AddItemViewHolder(val viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
}