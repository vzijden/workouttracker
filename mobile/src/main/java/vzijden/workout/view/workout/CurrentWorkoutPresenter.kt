package vzijden.workout.view.workout

import androidx.databinding.*
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set
import java.util.*

class CurrentWorkoutPresenter(var activity: View) : BaseObservable() {

  @get:Bindable
  var loggedWorkout: LoggedWorkout? = null
  @get:Bindable
  val currentExercisesPresenters = ObservableArrayList<CurrentExercisePresenter>()
  @Bindable
  val currentReps = ObservableField<String>()
  @Bindable
  val currentWeight = ObservableField<String>()
  @get:Bindable
  val currentRegistration = ObservableField<LoggedRegistration>()

  private var currentExerciseIndex = 0
  private var repsViewShowing = false

  fun loadWorkout(workoutId: Int) {
    doAsync {
      val workout = activity.getDatabase().workoutDao().getById(workoutId)
      val registrationAndSets = activity.getDatabase().registrationAndSetsDao().getAllForWorkout(workoutId)
      createLoggedWorkout(workout, registrationAndSets)
    }
  }

  fun finishRepClicked() {
    repsViewShowing = if (!repsViewShowing) {
      activity.showRepsView()
      true
    } else {
      activity.hideRepsView()
      logSet()
      if (currentExerciseIndex < currentExercisesPresenters.size) {
        setCurrentExercise(++currentExerciseIndex)
      }
      false
    }

  }

  private fun logSet() {
    val reps = currentReps.get()?.toInt() ?: 0
    val weight = currentWeight.get()?.toInt() ?: 0
    currentRegistration.get()?.let { currentRegistration ->
      val loggedSet = LoggedSet(Set(reps, currentRegistration.registration.id), weight)
      currentExercisesPresenters[currentExerciseIndex].loggedSets.add(loggedSet)
      doAsync {
        activity.getDatabase().setsDao().inserLogged(loggedSet)
      }
    }
  }

  private fun createLoggedWorkout(workout: Workout, registrationsAndSets: List<RegistrationAndSets>) {
    loggedWorkout = LoggedWorkout(workout, Date(), false)
    registrationsAndSets.forEach { registrationsAndSets ->
      val currentExercisePresenter = CurrentExercisePresenter()
      currentExercisePresenter.setRegistration(registrationsAndSets)
      currentExercisesPresenters.add(currentExercisePresenter)
    }
    setCurrentExercise(0)
    notifyPropertyChanged(BR.loggedWorkout)
  }

  private fun setCurrentExercise(index: Int) {
    currentExerciseIndex = index
    currentExercisesPresenters.getOrNull(index)?.let {
      currentRegistration.set(it.loggedRegistration)
    }
  }

  interface View {
    fun getDatabase(): ScheduleDatabase
    fun showRepsView()
    fun hideRepsView()
  }

  class CurrentExercisePresenter : BaseObservable() {
    @get:Bindable
    var loggedRegistration: LoggedRegistration? = null
    @get:Bindable
    val loggedSets: ObservableArrayList<LoggedSet> = ObservableArrayList()
    val plannedSets = ObservableArrayList<Set>()

    fun setRegistration(registrationAndSets: RegistrationAndSets) {
      plannedSets.addAll(registrationAndSets.sets)
      this.loggedRegistration = LoggedRegistration(registrationAndSets.registration)
    }
  }
}