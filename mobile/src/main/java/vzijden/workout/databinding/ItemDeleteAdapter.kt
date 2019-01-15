package vzijden.workout.databinding

interface ItemDeleteAdapter<T> {
  fun addOnItemDeletedListener(onItemDeletedListener: OnItemClickedListener<T>)
  fun deleteItem(viewHolderPosition: Int)
}