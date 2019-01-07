package vzijden.workout.view

interface BindableAdapter<T> {
  fun setData(items: List<T>)
  fun changedPositions(positions: Set<Int>)
}