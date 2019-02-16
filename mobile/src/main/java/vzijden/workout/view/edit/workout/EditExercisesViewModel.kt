package vzijden.workout.view.edit.workout

import androidx.databinding.*
import vzijden.workout.adapter.OnAddItemListener
import vzijden.workout.adapter.OnItemClickedListener
import vzijden.workout.adapter.OnItemDeletedListener
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.CreatePlannedExercise
import vzijden.workout.domain.usecase.DeletePlannedExercise
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.UpdateWorkout
import vzijden.workout.view.edit.workout.exercise.ExerciseItemViewModel

class EditExercisesViewModel(private val getWorkout: GetWorkout,
                             private val deletePlannedExercise: DeletePlannedExercise,
                             private val createPlannedExercise: CreatePlannedExercise) : BaseObservable() {
  val workout = ObservableField<PlannedWorkout>()
  var exercisesFragmentView: ExercisesFragmentView? = null
  var exerciseItemViewModels = ObservableArrayList<ExerciseItemViewModel>()
  var workoutId = 0L

  @get:Bindable
  val onItemDeletedListener = object : OnItemClickedListener<PlannedExercise> {
    override fun onItemClicked(item: PlannedExercise, pos: Int) {

    }

  }

  fun loadWorkout(workoutId: Long) {
    this.workoutId = workoutId
    getWorkout.execute(workoutId).firstElement().subscribe {
      workout.set(it)
      it.plannedExercises.forEachIndexed(this::loadExerciseViewModel)
    }
  }

  private fun loadExerciseViewModel(index: Int, plannedExercise: PlannedExercise) {
    exerciseItemViewModels.add(object : ExerciseItemViewModel(plannedExercise, index) {
      override fun onClick() {
      }

      override fun onAddItemClick() {
      }

      override fun onDeleteClick(setId: Int) {
      }

    })
  }

  val onAddExerciseClicked = object : OnAddItemListener {
    override fun onItemAddItemClicked() {
      exercisesFragmentView?.newRegistration(workoutId)
    }

  }

  val onExerciseDeleteClicked = object : OnItemDeletedListener<ExerciseItemViewModel> {
    override fun onItemDeleted(item: ExerciseItemViewModel, pos: Int) {
      deletePlannedExercise.execute(item.plannedExercise).subscribe {
        exerciseItemViewModels.removeAt(pos)
      }
    }
  }


  fun onNewWorkout() {

  }

  fun onNewExerciseSelected(exerciseId: Long) {
      createPlannedExercise.execute(CreatePlannedExercise.Params(exerciseId, workoutId)).subscribe { plannedExercise ->
        loadExerciseViewModel(exerciseItemViewModels.size - 1, plannedExercise)
      }
  }


  interface ExercisesFragmentView {
    fun openRegistration(plannedExercise: PlannedExercise, workoutId: Long)
    fun newRegistration(workoutId: Long)
  }
}



