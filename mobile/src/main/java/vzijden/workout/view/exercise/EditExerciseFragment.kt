package vzijden.workout.view.exercise

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
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.EditExerciseBinding
import vzijden.workout.databinding.EditExerciseSetItemViewBinding
import vzijden.workout.view.AbstractAdapter
import vzijden.workout.view.AddItemAdapter

class EditExerciseFragment : Fragment(), EditExercisePresenter.EditExercisesFragmentView {
  companion object {
    private const val REGISTRATION_ID = "RegistrationID"
    private const val WORKOUT_ID = "workoutID"

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
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise, container, false)
    return binding.root
  }


  override fun onResume() {
    super.onResume()

    arguments!!.apply {
      presenter = EditExercisePresenter(getInt(WORKOUT_ID)).apply {
        subscribe(this@EditExerciseFragment)
        binding.editExercisePresenter = this
      }

      if (containsKey(REGISTRATION_ID)) {
        presenter.loadRegistration(getInt(REGISTRATION_ID))
      } else {
        presenter.newRegistration()
      }
    }

    setupSetsRecyclerView()
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
    var sets: List<Set> = mutableListOf()


    override fun getItemCount(): Int {
      return sets.size + 1
    }

    override fun getHolderViewType(): Int = 1

    override fun setData(items: List<Set>) {
      sets = items
      notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      if (getHolderViewType() == viewType) {
        val inflater = LayoutInflater.from(parent.context)
        val binding: EditExerciseSetItemViewBinding = DataBindingUtil.inflate(inflater, R.layout.edit_exercise_set_item_view, parent, false)

        return SetViewHolder(binding)
      } else return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      if (holder.itemViewType == getHolderViewType()) {
        (holder as SetViewHolder).let {
          holder.binding.set = sets.get(position)
        }
      } else super.onBindViewHolder(holder, position)
    }

    class SetViewHolder(var binding: EditExerciseSetItemViewBinding) : RecyclerView.ViewHolder(binding.root)
  }
}