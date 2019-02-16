package vzijden.workout.adapter

interface OnItemDeletedListener<T> {
  fun onItemDeleted(item: T, pos: Int)
}