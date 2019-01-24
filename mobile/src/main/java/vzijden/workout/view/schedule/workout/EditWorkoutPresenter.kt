package vzijden.workout.view.schedule.workout

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set
import vzijden.workout.databinding.AddItemAdapter
import vzijden.workout.databinding.OnItemClickedListener

class EditWorkoutPresenter(private val scheduleDatabase: ScheduleDatabase) : BaseObservable() {
  val onItemDeletedListener = object : OnItemClickedListener<ExerciseItemPresenter> {
    override fun onItemClicked(item: ExerciseItemPresenter, pos: Int) {
      deleteRegistration(pos)
    }
  }

  private var workout: Workout? = null
  private var registrationsAndSets: MutableList<RegistrationAndSets> = mutableListOf()
  private var registrationBeingEdited = ObservableField<RegistrationAndSets>()
  var workoutName = ObservableField<String>()
  var exercisesFragmentView: ExercisesFragmentView? = null
  @get:Bindable
  var changedPositions: kotlin.collections.Set<Int> = setOf()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }

  @get:Bindable
  var exerciseItemPresenters: ObservableArrayList<ExerciseItemPresenter> = ObservableArrayList()

  fun loadWorkout(workoutId: Int) {
    doAsync {
      val byWorkoutId = scheduleDatabase.workoutDao().getById(workoutId)
      byWorkoutId.let {
        workout = it
        workoutName.set(it.name)
      }
    }

    doAsync {
      val allForWorkout = scheduleDatabase.registrationAndSetsDao().getAllForWorkout(workoutId)
      uiThread {
        allForWorkout.forEach { registrationsAndSets ->
          addRegistrationAndSet(registrationsAndSets)
        }

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

        uiThread {
          addRegistrationAndSet(newRegistrationAndSets)
        }
      }
    }
  }

  fun deleteRegistration(index: Int) {
    val registrationToDelete = registrationsAndSets[index].registration
    registrationsAndSets.removeAt(index)
    exerciseItemPresenters.removeAt(index)
    doAsync {
      scheduleDatabase.registrationDao().delete(registrationToDelete)
    }
  }

  private fun addRegistrationAndSet(registrationAndSets: RegistrationAndSets) {
    registrationsAndSets.add(registrationAndSets)
    val index = registrationsAndSets.size - 1
    exerciseItemPresenters.add(object : ExerciseItemPresenter() {
      override val sets: ObservableArrayList<Set> =
          ObservableArrayList<Set>().apply { addAll(registrationAndSets.sets) }

      override fun onClick(pos: Int) =
          onExerciseClicked(pos)

      override val index: ObservableField<Int> =
          ObservableField<Int>().apply { set(index) }

      override val muscleGroup: ObservableField<String> =
          ObservableField<String>().apply {
            set(registrationAndSets.registration.exercise?.muscleGroups?.getOrNull(0)?.normalName ?: "")
          }

      override val exerciseName: ObservableField<String> =
          ObservableField<String>().apply { set(registrationAndSets.registration.exercise?.name) }

      override fun onAddItemClick() {
        val set = Set(8, registrationAndSets.registration.id)
        doAsync { scheduleDatabase.setsDao().insert(set) }
        sets.add(set)
      }

    })
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
    abstract fun onAddItemClick()
  };

}