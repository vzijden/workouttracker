package vzijden.workout.view.workout

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import vzijden.workout.BR
import vzijden.workout.domain.usecase.AddLoggedSetToCurrentWorkout
import vzijden.workout.domain.usecase.GetOrCreateCurrentWorkout

class CurrentWorkoutViewModel(
    private val getOrCreateCurrentWorkout: GetOrCreateCurrentWorkout,
    private val addLoggedSetToCurrentWorkout: AddLoggedSetToCurrentWorkout): BaseObservable() {

  private lateinit var activity: Activity
  @get:Bindable
  val sets = ArrayList<ExerciseItemViewModel>()

  fun bind(activity: Activity) {
    this.activity = activity
    activity.showExercisesList()
  }

  fun load() {
    getOrCreateCurrentWorkout.execute().subscribe { loggedWorkout ->
      sets.clear()
      loggedWorkout.loggedSets.map { loggedSet ->
        sets.add(ExerciseItemViewModel(loggedSet.exercise))
      }
      notifyPropertyChanged(BR.sets)
    }
  }

  fun logSet() {

  }

  fun onAddExerciseClick() {
    this.activity.openSelectExercise()
  }

  fun onAddExerciseSelected(exerciseId: Long) {
    addLoggedSetToCurrentWorkout.execute(exerciseId).subscribe()
  }

  interface Activity {
    fun showExercisesList()
    fun openSelectExercise()
  }
}