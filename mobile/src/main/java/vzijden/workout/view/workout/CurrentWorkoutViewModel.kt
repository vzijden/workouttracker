package vzijden.workout.view.workout

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import vzijden.workout.BR
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.AddExerciseToCurrentWorkout
import vzijden.workout.domain.usecase.GetCurrentWorkout
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.LogSet

class CurrentWorkoutViewModel(
    private val getCurrentWorkout: GetCurrentWorkout,
    private val getWorkout: GetWorkout,
    private val addExerciseToCurrentWorkout: AddExerciseToCurrentWorkout,
    private val logSet: LogSet) : BaseObservable() {

  var activity: Activity? = null
  val plannedWorkout = ObservableField<PlannedWorkout>()
  val currentWorkout = ObservableField<LoggedWorkout>()
  val currentExercise = ObservableField<LoggedExercise>()
  @get:Bindable
  val exercises = ArrayList<ExerciseItemViewModel>()
  val currentRepsCount = ObservableInt()
  val currentWeightCount = ObservableInt()

  fun bind(activity: Activity) {
    this.activity = activity
  }

  fun load(plannedWorkoutId: Int) {
    getCurrentWorkout.execute(plannedWorkoutId).subscribe { loggedWorkout ->
      currentWorkout.set(loggedWorkout)
      exercises.clear()
      loggedWorkout.loggedExercises?.forEach { loggedExercise ->
        exercises.add(ExerciseItemViewModel().apply {
          setLoggedExercise(loggedExercise)
          setPlannedExercise(loggedExercise.exercise)
        })
        notifyPropertyChanged(BR.exercises)
      }
    }

    activity?.let { activity ->
      if (!activity.isShowingExercisesList()) {
        activity.showExercisesList()
      }
    }
  }

  fun logSet() {
    logSet.execute(LogSet.Params(currentRepsCount.get(), currentWeightCount.get()))
        .subscribe { loggedSet ->

        }
  }

  fun selectExercise(loggedExercise: LoggedExercise) {
    currentExercise.set(loggedExercise)
  }

  fun onAddExerciseClick()  {
    this.activity?.openSelectExercise()
  }

  fun onAddExerciseSelected(exerciseId: Int) {
    addExerciseToCurrentWorkout.execute(exerciseId)
  }

  interface Activity {
    fun showExercisesList()
    fun showExercise(loggedExercise: LoggedExercise)
    fun isShowingExercisesList(): Boolean
    fun openSelectExercise()
  }
}