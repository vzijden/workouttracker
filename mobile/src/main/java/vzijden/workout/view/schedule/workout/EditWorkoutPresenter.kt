package vzijden.workout.view.schedule.workout

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set

class EditWorkoutPresenter(private val scheduleDatabase: ScheduleDatabase) : BaseObservable() {
  private var workoutAndRegistrations: WorkoutAndRegistrations? = null
    set(value) {
      field = value
      notifyPropertyChanged(BR.exerciseItemPresenters)
    }

  var workoutName = ObservableField<String>()
  var registrationBeingEdited = ObservableField<RegistrationAndSets>()
  var exercisesFragmentView: ExercisesFragmentView? = null
  var registrationsAndSets: MutableList<RegistrationAndSets>? = null
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

  @get:Bindable
  var exerciseItemPresenters: List<ExerciseItemPresenter> = listOf()
    get() {
      return registrationsAndSets?.mapIndexed { pos, registrationAndSets ->
        object : ExerciseItemPresenter() {
          override val sets: ObservableArrayList<Set> =
              ObservableArrayList<Set>().apply { addAll(registrationAndSets.sets) }

          override fun onClick(pos: Int) =
              onExerciseClicked(pos)

          override val index: ObservableField<Int> =
              ObservableField<Int>().apply { set(pos) }

          override val muscleGroup: ObservableField<String> =
              ObservableField<String>().apply {
                set(registrationAndSets.registration.exercise?.muscleGroups?.getOrNull(0)?.normalName ?: "")
              }

          override val exerciseName: ObservableField<String> =
              ObservableField<String>().apply { set(registrationAndSets.registration.exercise?.name) }


        }
      } ?: listOf()
    }

  fun loadWorkout(workoutId: Int) {
    doAsync {
      val byWorkoutId = scheduleDatabase.workoutAndRegistrationsDao().byWorkoutId(workoutId)
      byWorkoutId?.let {
        workoutAndRegistrations = it
        workoutName.set(it.workout.name)
      }
      scheduleDatabase.registrationAndSetsDao().getAllForWorkout(workoutId).let {
        registrationsAndSets = it
      }

    }
  }

  fun newWorkoutAndExercises(): WorkoutAndRegistrations? {
    this.workoutAndRegistrations = WorkoutAndRegistrations();
    return this.workoutAndRegistrations!!
  }

  private fun onExerciseClicked(pos: Int) {
    try {
      registrationsAndSets?.get(pos)?.let { registrationAndSets ->
        registrationBeingEdited.set(registrationAndSets)
        this.exercisesFragmentView?.openRegistration(registrationAndSets, registrationAndSets.registration.workoutId)
      }
    } catch (e: IndexOutOfBoundsException) {
      Log.e(EditWorkoutPresenter::class.simpleName, "Exercise with index: $pos not found")
    }
  }

  fun onExerciseAdded() {
    workoutAndRegistrations?.workout?.id?.let {
      exercisesFragmentView?.newRegistration(it)
    }
  }

  interface ExercisesFragmentView {
    fun setWorkoutAndExercises(workoutAndRegistrations: WorkoutAndRegistrations)
    fun openRegistration(registrationAndSets: RegistrationAndSets, workoutId: Int)
    fun newRegistration(workoutId: Int)
  }

  abstract class ExerciseItemPresenter : BaseObservable() {
    @get:Bindable
    abstract val exerciseName: ObservableField<String>
    @get:Bindable
    abstract val index: ObservableField<Int>
    @get:Bindable
    abstract val muscleGroup: ObservableField<String>
    @get:Bindable
    abstract val sets: ObservableArrayList<Set>

    abstract fun onClick(pos: Int)
  };

}