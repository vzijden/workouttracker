package vzijden.workout.view.exercise

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Registration
import vzijden.workout.data.model.RegistrationAndSets
import vzijden.workout.data.model.Set

class EditExercisePresenter(private val workoutId: Int) : BaseObservable() {

  var fragment: EditExercisesFragmentView? = null
  var registrationAndSets = ObservableField<RegistrationAndSets>()
  var changedPositions = ObservableField<kotlin.collections.Set<Int>>()


  fun subscribe(fragment: EditExercisesFragmentView) {
    this.fragment = fragment
  }

  fun loadRegistration(registrationId: Int) {
    doAsync {
      fragment?.getDatabase()?.let { database ->
        val registrationAndSets = database.registrationAndSetsDao().get(registrationId)
        uiThread {
          this@EditExercisePresenter.registrationAndSets.set(registrationAndSets)
        }
      }
    }
  }

  fun newRegistration() {
    val registration = Registration(workoutId)
    registrationAndSets.set(RegistrationAndSets(registration))
  }

  fun addSet() {
    registrationAndSets.get()?.apply {
      registration.exercise?.let {exercise ->
        sets.add(Set(8, registration.id, exercise))
        changedPositions.set(setOf(sets.size - 1))
      } ?: kotlin.run {
        Log.w(EditExercisePresenter::class.simpleName, "Set added while exercise is not set")
      }
    }
  }


  interface EditExercisesFragmentView {
    fun getDatabase(): ScheduleDatabase
  }

}