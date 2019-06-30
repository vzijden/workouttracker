package vzijden.workout.view.edit.exercise

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import org.jetbrains.anko.support.v4.ctx
import vzijden.workout.R
import vzijden.workout.databinding.EditExerciseBinding
import vzijden.workout.view.exercises.SelectExerciseActivity

class EditExerciseFragment : Fragment(), EditExerciseViewModel.EditExercisesFragmentView {
  companion object {
    private const val REGISTRATION_ID = "RegistrationID"
    private const val WORKOUT_ID = "workoutID"
    private const val SELECT_EXERCISE = 1

    fun createInstance(registrationId: Long, workoutId: Long): EditExerciseFragment {
      val bundle = Bundle()
      bundle.putLong(REGISTRATION_ID, registrationId)
      bundle.putLong(WORKOUT_ID, workoutId)
      return EditExerciseFragment().apply {
        arguments = bundle
      }
    }

    fun createInstance(workoutId: Int): EditExerciseFragment {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID, workoutId)
      return EditExerciseFragment().apply {
        arguments = bundle
      }
    }
  }

  lateinit var setsRecyclerView: RecyclerView
  lateinit var binding: EditExerciseBinding
  lateinit var viewModel: EditExerciseViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementEnterTransition = TransitionSet().apply {
      addTransition(ChangeBounds())
      addTransition(ChangeTransform())
    }
    arguments!!.apply {
      viewModel = EditExerciseViewModel(getInt(WORKOUT_ID)).apply {
        subscribe(this@EditExerciseFragment)
      }

      if (containsKey(REGISTRATION_ID)) {
        viewModel.loadRegistration(getInt(REGISTRATION_ID))
      } else {
        viewModel.newRegistration()
      }
    }
  }

  override fun onStart() {
    super.onStart()
//    setupSetsRecyclerView()
  }

  override fun onPause() {
    super.onPause()
    arguments?.apply {
      viewModel.exercise.get()?.id?.let {
        putLong(REGISTRATION_ID, it)
      }
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise, container, false)
    binding.editExerciseViewModel = viewModel
    return binding.root
  }

  override fun selectExercise() {
    val intent = Intent(ctx, SelectExerciseActivity::class.java)
    startActivityForResult(intent, SELECT_EXERCISE)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == SELECT_EXERCISE) {
      if (resultCode == Activity.RESULT_OK) {
        data?.let {
          val exerciseId = it.getIntExtra(SelectExerciseActivity.RESULT_ID, -1)
          if (exerciseId != -1) {
            viewModel.selectExercise(exerciseId)
          }
        }
      } else fragmentManager?.popBackStack()
    }
  }

//  private fun setupSetsRecyclerView() {
//    setsRecyclerView = edit_exercise_sets_recycler_view
//    val setsAdapter = SetsAdapter()
//    setsRecyclerView.apply {
//      adapter = setsAdapter
//      layoutManager = LinearLayoutManager(context)
//    }

  }


//  class SetsAdapter : AbstractAdapter<PlannedSet>() {
//    override fun getHolderViewType(pos: Int): Int = 1
//
//    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
//      val inflater = LayoutInflater.from(parent.context)
//      val binding: EditExerciseSetItemViewBinding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise_set_item_view, parent, false)
//      binding.viewModel = SetItemViewModel()
//      return SetViewHolder(binding)
//    }
//
//    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//      (holder as SetViewHolder).let {
//        it.binding.viewModel?.load(list[position], position)
//      }
//    }
//
//    class SetViewHolder(var binding: EditExerciseSetItemViewBinding) : RecyclerView.ViewHolder(binding.root)
//  }
//}