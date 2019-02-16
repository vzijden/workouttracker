package vzijden.workout.databinding

import vzijden.workout.adapter.OnItemClickedListener

interface ItemDeleteAdapter<T> {
  fun addOnItemDeletedListener(onItemDeletedListener: OnItemClickedListener<T>)
  fun deleteItem(viewHolderPosition: Int)
}