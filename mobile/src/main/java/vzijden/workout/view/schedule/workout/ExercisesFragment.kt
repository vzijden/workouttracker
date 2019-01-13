package vzijden.workout.view.schedule.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.edit_workout.*
import kotlinx.android.synthetic.main.workout_item_view.*
import vzijden.workout.R
import vzijden.workout.data.model.RegistrationAndSets
import vzijden.workout.data.model.WorkoutAndRegistrations
import vzijden.workout.databinding.EditWorkoutBinding
import vzijden.workout.view.exercise.EditExerciseFragment


class ExercisesFragment : Fragment(), EditWorkoutPresenter.ExercisesFragmentView {
  override fun setWorkoutAndExercises(workoutAndRegistrations: WorkoutAndRegistrations) {
  }

  private lateinit var workoutAndRegistrations: WorkoutAndRegistrations

  var presenter: EditWorkoutPresenter? = null
    set(value) {
      field = value
      binding?.viewModel = value
    }

  private lateinit var adapter: EditWorkoutAdapter

  private var binding: EditWorkoutBinding? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementReturnTransition = TransitionSet().apply {
      addTransition(ChangeBounds())
      addTransition(ChangeTransform())
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (binding == null) {
      binding = DataBindingUtil.inflate(inflater, R.layout.edit_workout, container, false)
    }
    presenter?.let {
      binding!!.viewModel = it
    }
    return binding!!.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (activity as AppCompatActivity).setSupportActionBar(actionbar)
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    setupRecyclerView()
  }


  override fun newRegistration(workoutId: Int) {
    val editExerciseFragment = EditExerciseFragment.createInstance(workoutId)
    fragmentManager?.let {
      it.beginTransaction()
          .addToBackStack("")
//          .addSharedElement(cardview, (registrationAndSets.registration.exercise?.name ?: "") + "cardview")
//          .addSharedElement(workout_item_view_name, (registrationAndSets.registration.exercise?.name ?: "") + "textview")
          .replace(1, editExerciseFragment).commit()
    }
  }


  override fun openRegistration(registrationAndSets: RegistrationAndSets, workoutId: Int) {
    val editExerciseFragment = EditExerciseFragment.createInstance(registrationAndSets.registration.id, workoutId)
    fragmentManager?.let {
      it.beginTransaction()
        .addToBackStack("")
        .addSharedElement(cardview, (registrationAndSets.registration.exercise?.name ?: "") + "cardview")
        .addSharedElement(workout_item_view_name, (registrationAndSets.registration.exercise?.name ?: "") + "textview")
        .replace(1, editExerciseFragment).commit()
    }
  }


  private fun setupRecyclerView() {
    adapter = EditWorkoutAdapter()
    edit_schedule_recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = this@ExercisesFragment.adapter
    }
  }


}