package vzijden.workout.adapter

import androidx.databinding.ObservableArrayList

interface BindableAdapter2<T> {
  fun bindData(list: List<T>)
}