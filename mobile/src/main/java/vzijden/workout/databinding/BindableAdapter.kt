package vzijden.workout.databinding

interface BindableAdapter<T> {
  fun setData(items: List<T>)
  fun changedPositions(positions: Set<Int>)
  fun addOnItemClickedListener(listener: OnItemClickedListener<T>)
}