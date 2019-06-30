package vzijden.workout.adapter

interface ClickableAdapter<T> {
  fun addOnItemClickedListener(listener: OnItemClickedListener<T>)
}