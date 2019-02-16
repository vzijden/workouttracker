package vzijden.workout.adapter

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.domain.model.MuscleGroup

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: ObservableArrayList<T>?) {
  if (recyclerView.adapter is BindableAdapter<*>) {
    items?.let {
      (recyclerView.adapter as BindableAdapter<T>).bindData(it)
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
fun setItemAddedListener(recyclerView: RecyclerView, listener: OnAddItemListener?) {
  if (recyclerView.adapter is AddItemAdapter && listener != null) {
    (recyclerView.adapter as AddItemAdapter).addOnAddItemListener(listener)
  }
}

@BindingAdapter("onItemClicked")
fun <T> setOnItemClickedListener(recyclerView: RecyclerView, listener: OnItemClickedListener<T>) {
  if (recyclerView.adapter is BindableAdapter<*> && listener != null) {
    (recyclerView.adapter as BindableAdapter<T>).addOnItemClickedListener(listener)
  }
}

@BindingAdapter("onItemDeleted")
fun <T> setOnItemDeletedAdapter(recyclerView: RecyclerView, listener: OnItemDeletedListener<T>) {
  if (recyclerView.adapter is ItemDeleteAdapter<*> && listener != null) {
    (recyclerView.adapter as ItemDeleteAdapter<T>).addOnItemDeletedListener(listener)
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