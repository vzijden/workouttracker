package vzijden.workout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.App

abstract class AbstractBindableAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter<T>, ClickableAdapter<T> {
  private var onItemClickedListener: OnItemClickedListener<T>? = null

  protected var observableList = ObservableArrayList<T>()

  override fun addOnItemClickedListener(listener: OnItemClickedListener<T>) {
    onItemClickedListener = listener
  }

  final override fun changedPositions(positions: Set<Int>) {
    positions.forEach(this::notifyItemChanged)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return createItemViewHolder(inflater, parent)
  }


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder.itemViewType == getItemViewType(position)) {
      bindItemViewHolder(holder, position)
      holder.itemView.setOnClickListener {
        onItemClickedListener?.onItemClicked(observableList[position], position)
      }
    } else throw RuntimeException("Unknown viewType")
  }

  override fun getItemViewType(position: Int): Int {
    return getHolderViewType(position)
  }

  override fun getItemCount(): Int = observableList.size

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