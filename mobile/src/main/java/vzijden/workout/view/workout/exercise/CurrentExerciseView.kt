package vzijden.workout.view.workout.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_current_exercise.view.*
import vzijden.workout.BR
import vzijden.workout.R
import vzijden.workout.data.model.LoggedSet
import vzijden.workout.data.model.RegistrationAndLoggedSets
import vzijden.workout.data.model.RegistrationAndSets
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.CurrentExerciseSetItemLoggedBinding
import vzijden.workout.databinding.CurrentExerciseSetItemPlannedBinding
import vzijden.workout.databinding.ViewCurrentExerciseBinding
import java.util.*


class CurrentExerciseView(context: Context) : LinearLayout(context) {
  companion object {
    const val LOGGED_TYPE = 2
    const val PLANNED_TYPE = 3
  }

  var currentExerciseViewModel: CurrentExerciseViewModel = CurrentExerciseViewModel()
  var binding: ViewCurrentExerciseBinding

  private val currentExerciseAdapter = SetsAdapter()


  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = DataBindingUtil.inflate(inflater, R.layout.view_current_exercise, this, true)
  }

  fun setRegistrationAndSets(registrationAndSets: RegistrationAndSets, loggedSets: MutableList<LoggedSet>) {
    currentExerciseViewModel.setRegistration(registrationAndSets, loggedSets)
    binding.currentExerciseViewModel = currentExerciseViewModel

    fragment_current_exercise_recycler_view.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = currentExerciseAdapter
    }

    currentExerciseViewModel.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
      override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
        if (propertyId == BR.changedSets) {
          (sender as CurrentExerciseViewModel).changedSets.forEach(currentExerciseAdapter::notifyItemChanged)
        }
      }
    })

  }

  inner class SetsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      return if (viewType == LOGGED_TYPE) {
        val binding: CurrentExerciseSetItemLoggedBinding = DataBindingUtil.inflate(inflater,
            R.layout.current_exercise_set_item_logged, parent, false)
        LoggedSetViewHolder(binding)
      } else {
        val binding: CurrentExerciseSetItemPlannedBinding = DataBindingUtil.inflate(inflater,
            R.layout.current_exercise_set_item_planned, parent, false)
        PlannedSetViewHolder(binding)
      }
    }

    override fun getItemCount(): Int = Math.max(currentExerciseViewModel.plannedSets.size, currentExerciseViewModel.loggedSets.size)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      if (getItemViewType(position) == LOGGED_TYPE) {
        (holder as LoggedSetViewHolder).let {
          it.binding.loggedSet = currentExerciseViewModel.loggedSets[position]
        }
      } else {
        val set = currentExerciseViewModel.plannedSets[position]
        (holder as PlannedSetViewHolder).binding.set = set
      }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < currentExerciseViewModel.loggedSets.size) LOGGED_TYPE
        else PLANNED_TYPE
    }

    inner class LoggedSetViewHolder(var binding: CurrentExerciseSetItemLoggedBinding) : RecyclerView.ViewHolder(binding.root)


    inner class PlannedSetViewHolder(var binding: CurrentExerciseSetItemPlannedBinding) : RecyclerView.ViewHolder(binding.root)
  }

}