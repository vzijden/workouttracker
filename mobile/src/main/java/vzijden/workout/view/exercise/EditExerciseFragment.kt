package vzijden.workout.view.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_exercise.*
import vzijden.workout.R
import vzijden.workout.data.model.Set
import vzijden.workout.view.schedule.workout.exercise.set.SetView

class EditExerciseFragment : Fragment() {
  lateinit var setsRecyclerView: RecyclerView

 override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.edit_exercise, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupSetsRecyclerView()
  }

  private fun setupSetsRecyclerView() {
    setsRecyclerView = edit_exercise_sets_recycler_view
    val setsAdapter = SetsAdapter()
    edit_exercise_add_set_button.setOnClickListener {
      setsAdapter.addSet(Set(0, 8))
      setsAdapter.notifyItemInserted(setsAdapter.sets.size)
    }
    setsRecyclerView.apply {
      adapter = setsAdapter
      layoutManager = LinearLayoutManager(context)
    }
  }


  class SetsAdapter : RecyclerView.Adapter<SetsAdapter.SetViewHolder>() {
    var sets = ArrayList<Set>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SetViewHolder {
      val setItemView = LayoutInflater.from(p0.context).inflate(R.layout.set_item_view, p0, false) as SetView

      return SetViewHolder(setItemView)
    }

    override fun getItemCount(): Int {
      return sets.size
    }

    override fun onBindViewHolder(p0: SetViewHolder, p1: Int) {
      p0.setItemView.setAmount(sets[p1].reps)
    }

    fun addSet(set: Set) {
      sets.add(set)
    }

    class SetViewHolder(var setItemView: SetView) : RecyclerView.ViewHolder(setItemView)
  }
}