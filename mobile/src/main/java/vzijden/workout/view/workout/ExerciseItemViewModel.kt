package vzijden.workout.view.workout

import androidx.databinding.ObservableField
import vzijden.workout.domain.model.Exercise

class ExerciseItemViewModel(exercise: Exercise) {
  val exercise = ObservableField<Exercise>()
  val index = ObservableField<String>()

  init {
    this.exercise.set(exercise)
  }

}