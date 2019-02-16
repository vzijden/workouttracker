package vzijden.workout.adapter

interface ItemDeleteAdapter<T> {
  fun addOnItemDeletedListener(onItemDeletedListener: OnItemDeletedListener<T>)
  fun deleteItem(viewHolderPosition: Int)
}