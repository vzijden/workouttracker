package vzijden.workout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractBindableAdapter2<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableAdapter2<T>, ClickableAdapter<T> {
  private var onItemClickedListener: OnItemClickedListener<T>? = null

  protected var list: MutableList<T> = mutableListOf()

  override fun addOnItemClickedListener(listener: OnItemClickedListener<T>) {
    onItemClickedListener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return createItemViewHolder(inflater, parent)
  }


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder.itemViewType == getItemViewType(position)) {
      bindItemViewHolder(holder, position)
      holder.itemView.setOnClickListener {
        onItemClickedListener?.onItemClicked(list[position], position)
      }
    } else throw RuntimeException("Unknown viewType")
  }

  override fun getItemViewType(position: Int): Int {
    return getHolderViewType(position)
  }

  override fun getItemCount(): Int = list.size

  override fun bindData(newList: List<T>) {
    val diff = DiffUtil.calculateDiff(BindableAdapterDiffCallback(list, newList))
    list.clear()
    list.addAll(newList)
    diff.dispatchUpdatesTo(this)
  }

  abstract fun getHolderViewType(pos: Int): Int

  abstract fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

  abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)

  inner class AddItemViewHolder(val viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)

  inner class BindableAdapterDiffCallback<T>(private final val oldList: List<T>, private final val newList: List<T>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return newList[newItemPosition]?.equals(oldList[oldItemPosition]) ?: false
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return areItemsTheSame(oldItemPosition, newItemPosition)
    }

  }
}