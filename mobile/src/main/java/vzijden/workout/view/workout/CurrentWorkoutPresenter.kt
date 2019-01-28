package vzijden.workout.view.workout

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import vzijden.workout.App
import vzijden.workout.domain.model.LoggedSet
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.GetLoggedWorkout
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.LogSet
import vzijden.workout.domain.usecase.StartWorkout

class CurrentWorkoutPresenter(var activity: View, val getWorkout: GetWorkout, val startWorkout: StartWorkout,
                              val logSet: LogSet, val getLoggedWorkout: GetLoggedWorkout) : BaseObservable() {
  @get:Bindable
  lateinit var plannedWorkout: PlannedWorkout
  @get:Bindable
  lateinit var loggedWorkout: LoggedWorkout
  @Bindable
  val currentReps = ObservableField<String>()
  @Bindable
  val currentWeight = ObservableField<String>()

  private var currentRegistrationIndex = 0
  private var repsViewShowing = false

  @Bindable
  fun getCurrentRegistration(): PlannedExercise {
    return plannedWorkout.plannedExercises[currentRegistrationIndex]
  }

  fun loadWorkout(workoutId: Int) {
    doAsync {
      plannedWorkout = getWorkout.build(workoutId).blockingFirst()
      startWorkout()
    }
  }

  private fun startWorkout() {
    async {
      val loggedId = startWorkout.build(plannedWorkout.id).blockingGet()
      val loggedWorkout = getLoggedWorkout.build(loggedId.toInt())
      setCurrentExercise(0)
      notifyPropertyChanged(BR.workoutAndHistory)
    }
  }

  fun finishRepClicked() {
    repsViewShowing = if (!repsViewShowing) {
      activity.showRepsView()
      true
    } else {
      activity.hideRepsView()
      logSet()
      if (currentRegistrationIndex < plannedWorkout.plannedExercises.size) {
        setCurrentExercise(currentRegistrationIndex + 1)
      }
      false
    }

  }

  private fun logSet() {
    val reps = currentReps.get()?.toInt() ?: 0
    val weight = currentWeight.get()?.toInt() ?: 0
    getCurrentRegistration().let { currentRegistration ->
      val loggedSet = LoggedSet(currentRegistration, )

      doAsync {
        activity.getDatabase().setsDao().insertLogged(loggedSet)
        Log.d(App.TAG, "Inserted 1 loggedSet: $loggedSet")
      }
    }
  }

  private fun setCurrentExercise(index: Int) {
    if (index < workoutAndHistory.registrationsAndSets.size) {
      currentRegistrationIndex = index
      notifyPropertyChanged(BR.currentRegistration)
    }
  }

  interface View {
    fun getDatabase(): ScheduleDatabase
    fun showRepsView()
    fun hideRepsView()
  }

}