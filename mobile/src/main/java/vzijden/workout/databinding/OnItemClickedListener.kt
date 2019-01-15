package vzijden.workout.databinding

interface OnItemClickedListener<T> {
  fun onItemClicked(item: T, pos: Int)
}