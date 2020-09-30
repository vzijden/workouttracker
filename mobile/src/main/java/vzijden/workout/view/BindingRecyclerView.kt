package vzijden.workout.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.adapter.DataBindingAdapter


class BindingRecyclerView(context: Context, attr: AttributeSet) : RecyclerView(context, attr) {
  val dataBindingAdapter = DataBindingAdapter()


  init {
    val exercisesAdapter = dataBindingAdapter
    adapter = exercisesAdapter
    layoutManager = LinearLayoutManager(context)
  }
}
