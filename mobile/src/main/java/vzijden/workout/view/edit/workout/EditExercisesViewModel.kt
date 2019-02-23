package vzijden.workout.view.edit.workout

import androidx.databinding.*
import vzijden.workout.adapter.OnAddItemListener
import vzijden.workout.adapter.OnItemDeletedListener
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.*
import vzijden.workout.view.edit.workout.exercise.ExerciseItemViewModel

class EditExercisesViewModel(private val getWorkout: GetWorkout,
                             private val deletePlannedExercise: DeletePlannedExercise,
                             private val createPlannedExercise: CreatePlannedExercise,
                             private val deletePlannedSet: DeletePlannedSet,
                             private val createPlannedSet: CreatePlannedSet) : BaseObservable() {
  val workout = ObservableField<PlannedWorkout>()
  var exercisesFragmentView: ExercisesFragmentView? = null
  var exerciseItemViewModels = ObservableArrayList<ExerciseItemViewModel>()
  var workoutId = 0L

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
        createPlannedSet.execute(plannedExercise.id).subscribe { plannedSet ->
          addPlannedSet(plannedSet)
        }
      }

      override fun onDeleteClick(setId: Long) {
        deletePlannedSet.execute(setId).subscribe()
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



