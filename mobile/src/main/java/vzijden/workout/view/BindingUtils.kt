package vzijden.workout.view

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.data.model.MuscleGroup

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
  if (recyclerView.adapter is BindableAdapter<*>) {
    items?.let {
      (recyclerView.adapter as BindableAdapter<T>).setData(it)
    }
  }
}

@BindingAdapter("changedPositions")
fun <T> setDataChanged(recyclerView: RecyclerView, positions: Set<Int>?) {
  if (recyclerView.adapter is BindableAdapter<*> && positions != null) {
    (recyclerView.adapter as BindableAdapter<T>).changedPositions(positions)
  }
}

@BindingAdapter("onItemAdded")
fun setItemAddedListener(recyclerView: RecyclerView, listener: (() -> Unit)?) {
  if (recyclerView.adapter is AddItemAdapter && listener != null) {
    (recyclerView.adapter as AddItemAdapter).onAddItemClickedListener(listener)
  }
}

@BindingAdapter("muscleGroups")
fun setMuscleGroups(textView: TextView, muscleGroups: List<MuscleGroup>?) {
  var text = ""
  muscleGroups?.forEachIndexed { i, muscleGroup ->
    text += muscleGroup.normalName
    if (i != muscleGroups.size - 1)
      text += ", "
  }
  textView.text = text
}

@BindingAdapter("fromHtml")
fun setFromHtml(textView: TextView, htmlString: String?) {
  htmlString?.let {
    val fromHtml = Html.fromHtml(it)
    textView.text = fromHtml
  }
}
