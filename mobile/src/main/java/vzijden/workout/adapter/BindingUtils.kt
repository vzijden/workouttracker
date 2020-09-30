package vzijden.workout.adapter

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.domain.model.MuscleGroup
import vzijden.workout.view.BindingRecyclerView


@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: ObservableArrayList<T>?) {
  if (recyclerView.adapter is BindableAdapter2<*>) {
    items?.let {
      (recyclerView.adapter as BindableAdapter2<T>).bindData(it)
    }
  }
}

@BindingAdapter(value = ["data", "layoutId"])
fun <T> setRecyclerViewProperties(recyclerView: BindingRecyclerView, items: ObservableArrayList<T>?, layout: Int) {
  recyclerView.dataBindingAdapter.layoutId = layout
  if (recyclerView.adapter is BindableAdapter2<*>) {
    items?.let {
      (recyclerView.adapter as BindableAdapter2<T>).bindData(it)
    }
  }
}

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
  if (recyclerView.adapter is BindableAdapter2<*>) {
    items?.let {
      (recyclerView.adapter as BindableAdapter2<T>).bindData(it)
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
  if (recyclerView.adapter is ClickableAdapter<*> && listener != null) {
    (recyclerView.adapter as ClickableAdapter<T>).addOnItemClickedListener(listener)
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
