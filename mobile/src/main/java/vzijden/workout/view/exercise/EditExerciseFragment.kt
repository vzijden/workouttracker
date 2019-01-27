package vzijden.workout.view.exercise

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.edit_exercise.*
import org.jetbrains.anko.support.v4.ctx
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.EditExerciseBinding
import vzijden.workout.databinding.EditExerciseSetItemViewBinding
import vzijden.workout.view.AbstractAdapter
import vzijden.workout.view.exercises.SelectExerciseActivity

class EditExerciseFragment : Fragment(), EditExercisePresenter.EditExercisesFragmentView {
  companion object {
    private const val REGISTRATION_ID = "RegistrationID"
    private const val WORKOUT_ID = "workoutID"
    private const val SELECT_EXERCISE = 1

    fun createInstance(registrationId: Int, workoutId: Int): EditExerciseFragment {
      val bundle = Bundle()
      bundle.putInt(REGISTRATION_ID, registrationId)
      bundle.putInt(WORKOUT_ID, workoutId)
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
  lateinit var presenter: EditExercisePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementEnterTransition = TransitionSet().apply {
      addTransition(ChangeBounds())
      addTransition(ChangeTransform())
    }
    arguments!!.apply {
      presenter = EditExercisePresenter(getInt(WORKOUT_ID)).apply {
        subscribe(this@EditExerciseFragment)
      }

      if (containsKey(REGISTRATION_ID)) {
        presenter.loadRegistration(getInt(REGISTRATION_ID))
      } else {
        presenter.newRegistration()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    setupSetsRecyclerView()
  }

  override fun onPause() {
    super.onPause()
    arguments?.apply {
      presenter.registrationAndSets.get()?.registration?.id?.let {
        putInt(REGISTRATION_ID, it)
      }
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise, container, false)
    binding.editExercisePresenter = presenter
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
            presenter.selectExercise(exerciseId)
          }
        }
      } else fragmentManager?.popBackStack()
    }
  }

  override fun getDatabase(): ScheduleDatabase {
    return ScheduleDatabase.getInstance(context!!)
  }

  private fun setupSetsRecyclerView() {
    setsRecyclerView = edit_exercise_sets_recycler_view
    val setsAdapter = SetsAdapter()
    setsRecyclerView.apply {
      adapter = setsAdapter
      layoutManager = LinearLayoutManager(context)
    }

  }


  class SetsAdapter : AbstractAdapter<Set>() {
    override fun getHolderViewType(pos: Int): Int = 1


    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding: EditExerciseSetItemViewBinding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise_set_item_view, parent, false)

      return SetViewHolder(binding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      (holder as SetViewHolder).let {
        it.binding.set = observableList[position]
      }
    }

    class SetViewHolder(var binding: EditExerciseSetItemViewBinding) : RecyclerView.ViewHolder(binding.root)
  }
}