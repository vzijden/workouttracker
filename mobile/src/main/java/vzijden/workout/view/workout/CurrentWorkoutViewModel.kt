package vzijden.workout.view.workout

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import vzijden.workout.BR
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.usecase.AddLoggedSetToCurrentWorkout
import vzijden.workout.domain.usecase.CompleteSet
import vzijden.workout.domain.usecase.DeleteLoggedExerciseFromCurrentWorkout
import vzijden.workout.domain.usecase.GetOrCreateCurrentWorkout

class CurrentWorkoutViewModel(
    private val getOrCreateCurrentWorkout: GetOrCreateCurrentWorkout,
    private val addLoggedSetToCurrentWorkout: AddLoggedSetToCurrentWorkout,
    private val deleteLoggedExerciseFromCurrentWorkout: DeleteLoggedExerciseFromCurrentWorkout,
    private val completeSet: CompleteSet) : BaseObservable() {

  private lateinit var activity: Activity
  private var loggedWorkout: LoggedWorkout? = null

  val sets = ObservableArrayList<ExerciseItemViewModel>()

  @Bindable
  var currentWeight = "0"
  @Bindable
  var currentReps = "0"

  fun bind(activity: Activity) {
    this.activity = activity
    activity.showExercisesList()
  }

  fun load() {
    getOrCreateCurrentWorkout.execute().subscribe { loggedWorkout ->
      sets.clear()
      this.loggedWorkout = loggedWorkout
      loggedWorkout.loggedExercises.map { loggedExercise ->
        sets.add(ExerciseItemViewModel(loggedExercise))
      }
    }
  }

  fun onDelete(position: Int) {
    if (loggedWorkout?.loggedExercises?.size ?: 0 <= position)
      throw RuntimeException("Invalid index $position for delete set")

    loggedWorkout!!.loggedExercises[position].let { loggedExercise ->
      deleteLoggedExerciseFromCurrentWorkout.execute(loggedExercise).subscribe()
    }
  }

  fun onAddExerciseClick() {
    this.activity.openSelectExercise()
  }

  fun onAddExerciseSelected(exerciseId: Long) {
    addLoggedSetToCurrentWorkout.execute(exerciseId).subscribe()
  }

  fun isActiveSet(loggedSetId: Long) = loggedWorkout?.currentLoggedSetId == loggedSetId

  fun onCompleteSetClicked() {
    // todo: validation
    completeSet.execute(CompleteSet.Params(currentWeight.toInt(), currentReps.toInt()))
  }

  interface Activity {
    fun showExercisesList()
    fun openSelectExercise()
  }
}