package vzijden.workout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.adapter.AbstractAddItemAdapter2
import vzijden.workout.databinding.FragmentCurrentWorkoutListBinding
import vzijden.workout.databinding.FragmentCurrentWorkoutListBindingImpl
import vzijden.workout.databinding.FragmentCurrentWorkoutSetItemBindingImpl
import vzijden.workout.databinding.ItemCurrentWorkoutListBinding
import vzijden.workout.databinding.ItemCurrentWorkoutListBindingImpl
import vzijden.workout.domain.model.LoggedSet

class DataBindingAdapter : AbstractAddItemAdapter2<DataBindingAdapter.ViewHolder>() {
  companion object {
    private const val ITEM_VIEW_TYPE = 1
  }

  var layoutId: Int = -1
  set(value) {
    field = value
    notifyDataSetChanged()
  }

  override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
    if (layoutId != -1) {
      val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
      return ViewHolder(binding)
    }

    return ViewHolder(null)
  }

  override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    (holder as ViewHolder).binding?.setVariable(BR.viewModel, list[position])
  }

  override fun getHolderViewType(pos: Int): Int {
    return ITEM_VIEW_TYPE
  }

  inner class ViewHolder(val binding: ViewDataBinding?) : RecyclerView.ViewHolder(binding?.root ?: View(null))

}