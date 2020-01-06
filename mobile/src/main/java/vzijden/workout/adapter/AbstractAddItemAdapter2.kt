package vzijden.workout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_add_item_view.view.*
import vzijden.workout.R

abstract class AbstractAddItemAdapter2<T> : AbstractBindableAdapter2<T>(), AddItemAdapter {
  companion object {
    const val ADD_ITEM_VIEW_TYPE = 0
  }

  private var onAddItemListener: OnAddItemListener? = null

  final override fun addOnAddItemListener(onAddItemListener: OnAddItemListener) {
    this.onAddItemListener = onAddItemListener
    notifyItemChanged(list.size - 1)
  }

  final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder.itemViewType == ADD_ITEM_VIEW_TYPE) {
      (holder as AbstractBindableAdapter2<*>.AddItemViewHolder).let { addItemViewHolder ->
        addItemViewHolder.viewGroup.edit_workout_add_item_view_button.setOnClickListener {
          onAddItemListener?.onItemAddItemClicked()
        }
      }
    } else super.onBindViewHolder(holder, position)
  }

  final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return if (viewType == ADD_ITEM_VIEW_TYPE) AddItemViewHolder(inflater.inflate(R.layout.adapter_add_item_view, parent, false) as ViewGroup)
    else super.onCreateViewHolder(parent, viewType)
  }

  final override fun getItemViewType(position: Int): Int {
    return if (position == itemCount - 1) ADD_ITEM_VIEW_TYPE else super.getItemViewType(position)
  }

  final override fun getItemCount(): Int {
    return super.getItemCount() + 1
  }
}