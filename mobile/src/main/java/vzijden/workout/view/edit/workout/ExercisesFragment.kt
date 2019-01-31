package vzijden.workout.view.edit.workout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_edit_workout.*
import kotlinx.android.synthetic.main.activity_edit_workout_workout_item.*
import org.jetbrains.anko.support.v4.ctx
import vzijden.workout.R
import vzijden.workout.databinding.ActivityEditWorkoutBinding
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.UpdateWorkout
import vzijden.workout.view.edit.exercise.EditExerciseFragment
import vzijden.workout.view.exercises.SelectExerciseActivity
import javax.inject.Inject


class ExercisesFragment : DaggerFragment(), EditWorkoutViewModel.ExercisesFragmentView {
  companion object {
    private const val SELECT_EXERCISE_INTENT = 1
    private const val WORKOUT_ID_ARGUMENT = "workout"

    fun createInstance(workoutId: Int): ExercisesFragment {
      val bundle = Bundle()
      bundle.putInt(WORKOUT_ID_ARGUMENT, workoutId)
      val exercisesFragment = ExercisesFragment()
      exercisesFragment.arguments = bundle
      return exercisesFragment
    }
  }

  private lateinit var viewModel: EditWorkoutViewModel
  private lateinit var adapter: EditWorkoutAdapter
  @Inject
  lateinit var getWorkout: GetWorkout
  @Inject
  lateinit var updateWorkout: UpdateWorkout

  private var binding: ActivityEditWorkoutBinding? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementReturnTransition = TransitionSet().apply {
      addTransition(ChangeBounds())
      addTransition(ChangeTransform())
    }

    viewModel = EditWorkoutViewModel(getWorkout, updateWorkout)
    viewModel.exercisesFragmentView = this
    if (arguments?.containsKey(WORKOUT_ID_ARGUMENT) == true) {
      viewModel.loadWorkout(arguments!!.getInt(WORKOUT_ID_ARGUMENT, 0))
    } else {
      viewModel.newWorkout()
    }

  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.activity_edit_workout, container, false)
    binding!!.viewModel = viewModel
    return binding!!.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (activity as AppCompatActivity).setSupportActionBar(actionbar)
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    setupRecyclerView()
  }


  override fun newRegistration(workoutId: Int) {
    val intent = Intent(ctx, SelectExerciseActivity::class.java)
    startActivityForResult(intent, SELECT_EXERCISE_INTENT)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == SELECT_EXERCISE_INTENT) {
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


  override fun openRegistration(plannedExercise: PlannedExercise, workoutId: Int) {
    val editExerciseFragment = EditExerciseFragment.createInstance(plannedExercise.id, workoutId)
    fragmentManager?.let {
      it.beginTransaction()
          .addToBackStack("")
          .addSharedElement(cardview, (plannedExercise.exercise.name ?: "") + "cardview")
          .addSharedElement(workout_item_view_name, (plannedExercise.exercise.name
              ?: "") + "textview")
          .replace(1, editExerciseFragment).commit()
    }
  }


  private fun setupRecyclerView() {
    adapter = EditWorkoutAdapter()

    val editWorkoutAdapter = this@ExercisesFragment.adapter
    edit_schedule_recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = editWorkoutAdapter
    }

    val swipeController = object : ItemTouchHelper.Callback() {
      override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return if (viewHolder.itemViewType == 1)
          makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT)
        else makeMovementFlags(0, 0)
      }

      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return true
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        editWorkoutAdapter.deleteItem(viewHolder.adapterPosition)
      }

    }

    val itemTouchHelper = ItemTouchHelper(swipeController)
    itemTouchHelper.attachToRecyclerView(edit_schedule_recyclerView)

  }


}