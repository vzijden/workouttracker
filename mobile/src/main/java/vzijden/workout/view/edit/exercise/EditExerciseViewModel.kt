package vzijden.workout.view.edit.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import org.jetbrains.anko.doAsync
import vzijden.workout.domain.model.Exercise
import vzijden.workout.domain.model.PlannedSet

class EditExerciseViewModel(private val workoutId: Int) : BaseObservable() {

  lateinit var fragment: EditExercisesFragmentView

  var exercise = ObservableField<Exercise>()
  var sets = ObservableArrayList<PlannedSet>()
  var changedPositions = ObservableField<kotlin.collections.Set<Int>>()

  fun subscribe(fragment: EditExercisesFragmentView) {
    this.fragment = fragment

    doAsync {

    }

  }

  fun loadRegistration(registrationId: Int) {
    doAsync {

    }
  }

  fun newRegistration() {
    fragment.selectExercise()
  }

  fun addSet() {

  }

  fun selectExercise(exerciseId: Int) {
  }

  interface EditExercisesFragmentView {
    fun selectExercise()
  }
}