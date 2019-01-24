package vzijden.workout.view.exercise

import androidx.databinding.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.Registration
import vzijden.workout.data.model.RegistrationAndSets
import vzijden.workout.data.model.Set

class EditExercisePresenter(private val workoutId: Int) : BaseObservable() {

  lateinit var fragment: EditExercisesFragmentView

  var registrationAndSets = ObservableField<RegistrationAndSets>()
  var sets = ObservableArrayList<Set>()
  var changedPositions = ObservableField<kotlin.collections.Set<Int>>()
  var exercises = ObservableArrayList<Exercise>()

  fun subscribe(fragment: EditExercisesFragmentView) {
    this.fragment = fragment

    doAsync {
      fragment.getDatabase().apply {
        val all = exerciseDao().getAll()
        exercises.addAll(all)
      }
    }

  }

  fun loadRegistration(registrationId: Int) {
    doAsync {
      fragment.getDatabase().let { database ->
        val registrationAndSets = database.registrationAndSetsDao().get(registrationId)
        uiThread {
          this@EditExercisePresenter.registrationAndSets.apply {
            set(registrationAndSets)
//            notifyChange()
            sets.addAll(registrationAndSets.sets)
          }
        }
      }
    }
  }

  fun newRegistration() {
    fragment.selectExercise()
  }

  fun addSet() {
    registrationAndSets.get()?.apply {
      sets.add(Set(8, registration.id))
      changedPositions.set(setOf(sets.size - 1))
    }
  }

  fun selectExercise(exerciseId: Int) {
    val registrationAndSets = this.registrationAndSets.get() ?: RegistrationAndSets(Registration(workoutId))
    doAsync {
      val selectedExercise = fragment.getDatabase().exerciseDao().get(exerciseId)
      registrationAndSets.registration.exercise = selectedExercise
      registrationAndSets.sets.add(Set(8, registrationAndSets.registration.id))
      fragment.getDatabase().registrationDao().insert(registrationAndSets.registration)

      uiThread {
        it.registrationAndSets.set(registrationAndSets)
      }
    }
  }


  interface EditExercisesFragmentView {
    fun getDatabase(): ScheduleDatabase
    fun selectExercise()
  }

}