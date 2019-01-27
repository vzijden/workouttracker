package vzijden.workout.view.workout.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import vzijden.workout.BR
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set

class CurrentExerciseViewModel : BaseObservable() {
  var registration: Registration? = null
  val loggedSets = ObservableArrayList<LoggedSet>()
  val plannedSets = ObservableArrayList<Set>()
  @get:Bindable
  var changedSets: MutableSet<Int> = mutableSetOf()

  fun setRegistration(registrationAndSets: RegistrationAndSets, loggedSets: MutableList<LoggedSet>) {
    this.registration = registrationAndSets.registration

    loggedSets.clear()
    plannedSets.clear()
    changedSets.clear()
    loggedSets.forEachIndexed { index, loggedSet ->
      loggedSets.add(loggedSet)
      changedSets.add(index)
    }
    registrationAndSets.sets.forEachIndexed { index, set ->
      plannedSets.add(set)
      changedSets.add(index)
    }
    notifyPropertyChanged(BR.changedSets)
  }
}