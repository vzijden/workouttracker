package vzijden.workout.view.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_current_exercise.view.*
import vzijden.workout.R
import vzijden.workout.data.model.LoggedSet
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.FragmentCurrentExerciseSetItemBinding
import vzijden.workout.databinding.ViewCurrentExerciseBinding
import vzijden.workout.view.AbstractAdapter


class CurrentExerciseView(context: Context) : LinearLayout(context) {
  var binding: ViewCurrentExerciseBinding
  private val currentExerciseAdapter = CurrentExerciseAdapter()

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = DataBindingUtil.inflate(inflater, R.layout.view_current_exercise, this, true)
    fragment_current_exercise_recycler_view.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = currentExerciseAdapter
    }
  }

  fun setRegistrationAndSets(currentExercisePresenter: CurrentWorkoutPresenter.CurrentExercisePresenter) {
    binding.currentExercisePresenter = currentExercisePresenter
    currentExerciseAdapter.notifyDataSetChanged()
  }

  inner class CurrentExerciseAdapter : AbstractAdapter<LoggedSet>() {
    override fun getHolderViewType(): Int = 1

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding: FragmentCurrentExerciseSetItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_current_exercise_set_item, parent, false)

      return SetViewHolder(binding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      (holder as SetViewHolder).let {
        it.binding.loggedSet = observableList[position]
      }
    }

    inner class SetViewHolder(var binding: FragmentCurrentExerciseSetItemBinding) : RecyclerView.ViewHolder(binding.root)

  }

}