package vzijden.workout.view.edit.workout.exercise.set

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableInt
import vzijden.workout.domain.model.PlannedSet

abstract class SetItemViewModel : BaseObservable() {
  val index = ObservableInt()
  var id = 0L
  var reps = ObservableInt()

  fun load(plannedSet: PlannedSet, index: Int) {
    this.index.set(index)
    reps.set(plannedSet.reps)
    id = plannedSet.id

  }

   abstract fun onDeleteClick(setId: Long)
}