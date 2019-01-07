package vzijden.workout.view.schedule.workout.exercise.set

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vzijden.workout.R
import vzijden.workout.data.model.Set

class SetsAdapter : RecyclerView.Adapter<SetsAdapter.ViewHolder>() {
  private var sets: List<Set>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.set_item_view, parent, false) as ViewGroup)
  }

  override fun getItemCount(): Int {
    return sets?.size ?: 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  }

  inner class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)
}