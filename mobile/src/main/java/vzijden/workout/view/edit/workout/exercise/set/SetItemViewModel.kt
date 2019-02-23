package vzijden.workout.view.edit.workout.exercise.set

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableInt
import vzijden.workout.domain.model.PlannedSet

abstract class SetItemViewModel(plannedSet: PlannedSet) : BaseObservable() {
  val index = ObservableInt()
  var id = 0L
  var reps = ObservableInt()

  init {
//    this.index.set(plannedSe)
    reps.set(plannedSet.reps)
    id = plannedSet.id
    index.set(plannedSet.index + 1)
  }

   abstract fun onDeleteClick()
}