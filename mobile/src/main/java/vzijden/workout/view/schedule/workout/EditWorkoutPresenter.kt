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
import vzijden.workout.databinding.OnItemClickedListener

class EditWorkoutPresenter(private val scheduleDatabase: ScheduleDatabase) : BaseObservable() {
  val onItemDeletedListener = object: OnItemClickedListener<ExerciseItemPresenter> {
    override fun onItemClicked(item: ExerciseItemPresenter, pos: Int) {

    }
  }

  private var workout: Workout? = null
  private var registrationsAndSets: MutableList<RegistrationAndSets> = mutableListOf()
  var workoutName = ObservableField<String>()
  var registrationBeingEdited = ObservableField<RegistrationAndSets>()
  var exercisesFragmentView: ExercisesFragmentView? = null

  @get:Bindable
  var changedPositions = mutableSetOf<Int>()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }

  @get:Bindable
  var exerciseItemPresenters: List<ExerciseItemPresenter> = listOf()
    get() {
      return registrationsAndSets.mapIndexed { pos, registrationAndSets ->
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
      }
    }

  fun loadWorkout(workoutId: Int) {
    doAsync {
      val byWorkoutId = scheduleDatabase.workoutDao().getById(workoutId)
      byWorkoutId.let {
        workout = it
        workoutName.set(it.name)
      }
    }

    doAsync {
      scheduleDatabase.registrationAndSetsDao().getAllForWorkout(workoutId).let {
        registrationsAndSets = it
        notifyPropertyChanged(BR.exerciseItemPresenters)
      }
    }
  }

  fun newWorkout(): Workout {
    this.workout = Workout(0, "Workout1", 0)
    return this.workout!!
  }

  fun onAddExerciseClick() {
    workout?.id?.let {
      exercisesFragmentView?.newRegistration(it)
    }
  }

  fun onExerciseSelected(exerciseId: Int) {
    workout?.let { workout ->
      doAsync {
        val selectedExercise = scheduleDatabase.exerciseDao().get(exerciseId)
        val newRegistration = Registration(workout.id, selectedExercise)
        val newRegistrationAndSets = RegistrationAndSets(newRegistration)
        val newRegistrationId = scheduleDatabase.registrationDao().insert(newRegistration)
        val set = Set(8, newRegistrationId.toInt())
        newRegistrationAndSets.sets.add(set)
        scheduleDatabase.setsDao().insert(set)
        loadWorkout(workout.id)

      }
    }
  }

  fun deleteRegistration(registration: Registration) {
    doAsync {
      scheduleDatabase.registrationDao().delete(registration)
      registrationsAndSets.removeIf { it.registration.id == registration.id }
    }
  }

  private fun onExerciseClicked(pos: Int) {
    try {
      registrationsAndSets[pos].let { registrationAndSets ->
        registrationBeingEdited.set(registrationAndSets)
        this.exercisesFragmentView?.openRegistration(registrationAndSets, registrationAndSets.registration.workoutId)
      }
    } catch (e: IndexOutOfBoundsException) {
      Log.e(EditWorkoutPresenter::class.simpleName, "Exercise with index: $pos not found")
    }
  }

  interface ExercisesFragmentView {
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