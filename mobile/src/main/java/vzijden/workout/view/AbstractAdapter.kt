package vzijden.workout.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_workout_add_item_view.view.*
import vzijden.workout.App
import vzijden.workout.R
import vzijden.workout.adapter.AddItemAdapter
import vzijden.workout.adapter.BindableAdapter
import vzijden.workout.adapter.OnAddItemListener
import vzijden.workout.adapter.OnItemClickedListener
import java.lang.RuntimeException

abstract class AbstractAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<T>, AddItemAdapter {
  companion object {
    const val ADD_ITEM_VIEW_TYPE = 0
  }

  private var onAddItemClicked: OnAddItemListener? = null
  private var onItemClickedListener: OnItemClickedListener<T>? = null

  protected var observableList = ObservableArrayList<T>()

  override fun addOnAddItemListener(onItemAddItemListener: OnAddItemListener) {
    this.onAddItemClicked = onItemAddItemListener
    notifyItemChanged(observableList.size - 1)
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
      viewType -> createItemViewHolder(inflater, parent)
      else -> throw RuntimeException("Unknown viewType")
    }
  }


  final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder.itemViewType) {
      ADD_ITEM_VIEW_TYPE -> {
        (holder as AbstractAdapter<*>.AddItemViewHolder).let { addItemViewHolder ->
          addItemViewHolder.viewGroup.edit_workout_add_item_view_button.setOnClickListener {
            onAddItemClicked?.onItemAddItemClicked()
          }
        }
      }
      getItemViewType(position) -> {
        bindItemViewHolder(holder, position)
        holder.itemView.setOnClickListener {
          onItemClickedListener?.onItemClicked(observableList[position], position)
        }
      }
      else -> throw RuntimeException("Unknown viewType")
    }
  }

  final override fun getItemViewType(position: Int): Int {
    return if (onAddItemClicked != null && position == itemCount - 1) ADD_ITEM_VIEW_TYPE else getHolderViewType(position)
  }

  final override fun getItemCount(): Int {
    return if (onAddItemClicked != null) {
      observableList.size + 1
    } else observableList.size
  }

  override fun bindData(observableList: ObservableArrayList<T>) {
    if (this.observableList.isEmpty()) {
      this.observableList = observableList
      this.observableList.addOnListChangedCallback(onListChangedCallback)
      notifyDataSetChanged()
      Log.d(App.TAG, "AbstractAdapter observable list bound with: ${observableList.size} items")
    }
  }

  abstract fun getHolderViewType(pos: Int): Int

  abstract fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

  abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)

  private val onListChangedCallback = object : ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
    override fun onChanged(sender: ObservableArrayList<T>?) {
      notifyDataSetChanged()
      Log.d(App.TAG, "AbstractAdapter observable list changed")
    }

    override fun onItemRangeRemoved(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) {
      notifyItemRangeRemoved(positionStart, itemCount)
      if (positionStart != observableList.size) {
        notifyItemRangeChanged(positionStart, observableList.size)
      }
    }

    override fun onItemRangeMoved(sender: ObservableArrayList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
      notifyItemRangeChanged(fromPosition, itemCount)
    }

    override fun onItemRangeInserted(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) {
      notifyItemRangeInserted(positionStart, itemCount)
      Log.d(App.TAG, "AbstractAdapter $itemCount inserted at: $positionStart")
    }

    override fun onItemRangeChanged(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) {
      notifyItemRangeChanged(positionStart, itemCount)
    }

  }

  inner class AddItemViewHolder(val viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
}