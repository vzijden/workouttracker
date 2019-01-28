package vzijden.workout.view.workout.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedSet

class CurrentExerciseViewModel : BaseObservable() {
  var registration: PlannedExercise? = null
  val loggedSets = ObservableArrayList<LoggedSet>()
  val plannedSets = ObservableArrayList<PlannedSet>()
  @get:Bindable
  var changedSets: MutableSet<Int> = mutableSetOf()

  fun setRegistration(plannedExercise: PlannedExercise, loggedSets: MutableList<LoggedSet>) {
    this.registration = plannedExercise

    loggedSets.clear()
    plannedSets.clear()
    changedSets.clear()
    loggedSets.forEachIndexed { index, loggedSet ->
      loggedSets.add(loggedSet)
      changedSets.add(index)
    }
    plannedExercise.plannedSets?.forEachIndexed { index, set ->
      plannedSets.add(set)
      changedSets.add(index)
    }
    notifyPropertyChanged(BR.changedSets)
  }
}