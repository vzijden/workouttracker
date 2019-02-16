package vzijden.workout.databinding

import androidx.databinding.ObservableArrayList
import vzijden.workout.adapter.OnItemClickedListener

interface BindableAdapter<T> {
  fun changedPositions(positions: Set<Int>)
  fun addOnItemClickedListener(listener: OnItemClickedListener<T>)
  fun bindData(observableList: ObservableArrayList<T>)
}