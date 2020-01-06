package vzijden.workout.view.edit.workout.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.view.edit.workout.exercise.set.SetItemViewModel

abstract class ExerciseItemViewModel(val plannedExercise: PlannedExercise, mIndex: Int) : BaseObservable() {

  val name = ObservableField<String>()
  val muscleGroup = ObservableField<String>()
  val plannedSets: ObservableArrayList<SetItemViewModel> = ObservableArrayList()
  val index = ObservableField<String>()

  init {
    name.set(plannedExercise.exercise.name)
    muscleGroup.set(plannedExercise.exercise.muscleGroups.firstOrNull()?.normalName ?: "")
    // mIndex is zero-based
    index.set((mIndex + 1).toString())
    plannedExercise.plannedSets.forEach(this::addPlannedSet)
  }

  protected fun addPlannedSet(plannedSet: PlannedSet) {
    plannedSets.add(object : SetItemViewModel(plannedSet) {
      override fun onDeleteClick() {
        this@ExerciseItemViewModel.plannedSets.remove(this)
        this@ExerciseItemViewModel.onDeleteClick(plannedSet.id)
      }
    })
  }


  abstract fun onClick()

  abstract fun onAddItemClick()

  abstract fun onDeleteClick(setId: Int)

}
