package vzijden.workout.adapter

interface OnItemClickedListener<T> {
  fun onItemClicked(item: T, pos: Int)
}