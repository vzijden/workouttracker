package vzijden.workout.view.workout

import androidx.databinding.ObservableField
import vzijden.workout.domain.model.Exercise
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.PlannedExercise

class ExerciseItemViewModel {
  val loggedExercise = ObservableField<LoggedExercise>()
  val exercise = ObservableField<Exercise>()
  val index = ObservableField<String>()

  fun setExercise(exercise: Exercise) {
    this.exercise.set(exercise)
  }

  fun setLoggedExercise(loggedExercise: LoggedExercise) {
    this.loggedExercise.set(loggedExercise)
    this.index.set((loggedExercise.index + 1).toString())
  }
}