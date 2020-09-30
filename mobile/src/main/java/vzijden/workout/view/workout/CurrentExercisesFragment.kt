package vzijden.workout.view.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_current_workout_list.*
import vzijden.workout.R.layout
import vzijden.workout.databinding.FragmentCurrentWorkoutListBinding

class CurrentExercisesFragment : Fragment() {
  var binding: FragmentCurrentWorkoutListBinding? = null
  var viewModel: CurrentWorkoutViewModel? = null
    set(value) {
      binding?.viewModel = value
      field = value
    }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, layout.fragment_current_workout_list, container, false)
    return binding!!.root
  }

  override fun onStart() {
    super.onStart()
    setupRecyclerView()
    viewModel?.let { viewModel ->
      binding!!.viewModel = viewModel
    }
  }

  private fun setupRecyclerView() {
    val exercisesAdapter = ExercisesAdapter()
    fragment_current_workout_list_recyclerview.apply {
      adapter = exercisesAdapter
      layoutManager = LinearLayoutManager(this@CurrentExercisesFragment.context)
    }
  }
}