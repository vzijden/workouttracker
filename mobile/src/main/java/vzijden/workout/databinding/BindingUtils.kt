package vzijden.workout.databinding

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.adapter.AddItemAdapter
import vzijden.workout.adapter.BindableAdapter
import vzijden.workout.adapter.BindableAdapter2
import vzijden.workout.adapter.ClickableAdapter
import vzijden.workout.adapter.ItemDeleteAdapter
import vzijden.workout.adapter.OnAddItemListener
import vzijden.workout.adapter.OnItemClickedListener
import vzijden.workout.adapter.OnItemDeletedListener
import vzijden.workout.domain.model.MuscleGroup

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: ObservableArrayList<T>?) {
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

@BindingAdapter("onDeleteSwipe")
fun <T> setOnItemDeletedAdapter(recyclerView: RecyclerView, listener: OnItemDeletedListener) {
  if (recyclerView.adapter is ItemDeleteAdapter) {
    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
      override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
          makeMovementFlags(0, RIGHT)

      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) =
          true

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemDeleted(viewHolder.layoutPosition)
      }
    })
    itemTouchHelper.attachToRecyclerView(recyclerView)
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
