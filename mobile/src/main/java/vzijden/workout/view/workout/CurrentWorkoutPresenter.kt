package vzijden.workout.view.workout

import android.util.Log
import androidx.databinding.*
import org.jetbrains.anko.doAsync
import vzijden.workout.App
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set
import java.util.*

class CurrentWorkoutPresenter(var activity: View) : BaseObservable() {
  @get:Bindable
  lateinit var workoutAndHistory: WorkoutAndHistory
  @get:Bindable
  lateinit var loggedWorkoutAndRegistrations: LoggedWorkoutAndRegistrations
  @Bindable
  val currentReps = ObservableField<String>()
  @Bindable
  val currentWeight = ObservableField<String>()

  private var currentRegistrationIndex = 0
  private var repsViewShowing = false

  @Bindable
  fun getCurrentRegistration(): Registration {
    return workoutAndHistory.registrationsAndSets[currentRegistrationIndex].registration
  }

  fun loadWorkout(workoutId: Int) {
    doAsync {
      workoutAndHistory = activity.getDatabase().workoutDao().getWorkoutAndHistory(workoutId)
      startWorkout()
    }
  }

  private fun startWorkout() {
    val loggedWorkout = LoggedWorkout(workoutAndHistory.workout, Date());
    loggedWorkoutAndRegistrations = LoggedWorkoutAndRegistrations(loggedWorkout)
    setCurrentExercise(0)
    notifyPropertyChanged(BR.workoutAndHistory)
  }

  fun finishRepClicked() {
    repsViewShowing = if (!repsViewShowing) {
      activity.showRepsView()
      true
    } else {
      activity.hideRepsView()
      logSet()
      if (currentRegistrationIndex < workoutAndHistory.registrationsAndSets.size) {
        setCurrentExercise(currentRegistrationIndex + 1)
      }
      false
    }

  }

  private fun logSet() {
    val reps = currentReps.get()?.toInt() ?: 0
    val weight = currentWeight.get()?.toInt() ?: 0
    getCurrentRegistration().let { currentRegistration ->
      val loggedSet = LoggedSet(Set(reps, currentRegistration.id), weight, workoutAndHistory.workout.id)

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