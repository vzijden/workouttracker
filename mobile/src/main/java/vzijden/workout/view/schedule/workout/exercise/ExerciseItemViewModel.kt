package vzijden.workout.view.schedule.workout.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import vzijden.workout.BR
import vzijden.workout.data.model.Set

class ExerciseItemViewModel: BaseObservable() {
  var exerciseName = ObservableField<String>()
  var muscleGroup = ObservableField<String>()
  var index = ObservableField<String>()

  @get:Bindable
  var sets = mutableListOf<Set>()
  set(value) {
    field = value
    notifyPropertyChanged(BR.sets)
  }

  @get:Bindable
  var changedPositions = mutableSetOf<Int>()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }

}