package vzijden.workout.view.workout

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import vzijden.workout.domain.model.LoggedExercise
import vzijden.workout.domain.model.PlannedExercise

class ExerciseItemViewModel {
  val loggedExercise = ObservableField<LoggedExercise>()
  val plannedExercise = ObservableField<PlannedExercise>()
  val index = ObservableField<String>()

  fun setPlannedExercise(plannedExercise: PlannedExercise) {
    this.plannedExercise.set(plannedExercise)
    this.index.set((plannedExercise.index + 1).toString())
  }

  fun setLoggedExercise(loggedExercise: LoggedExercise) {
    this.loggedExercise.set(loggedExercise)
  }
}