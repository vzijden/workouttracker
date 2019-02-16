package vzijden.workout.adapter

import androidx.databinding.ObservableArrayList

interface BindableAdapter<T> {
  fun changedPositions(positions: Set<Int>)
  fun addOnItemClickedListener(listener: OnItemClickedListener<T>)
  fun bindData(observableList: ObservableArrayList<T>)
}