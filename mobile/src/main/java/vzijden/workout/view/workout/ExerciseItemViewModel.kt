package vzijden.workout.view.workout

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import vzijden.workout.domain.model.Exercise
import vzijden.workout.domain.model.LoggedExercise

class ExerciseItemViewModel(loggedExercise: LoggedExercise) {
  val exercise = ObservableField<Exercise>()
  val sets = ObservableArrayList<LoggedSetViewModel>()
  val index = ObservableField<String>()
  val setCount: Int
  val setsCompleted: Int
  val focused = false

  init {
    this.exercise.set(loggedExercise.exercise)
    this.setCount = loggedExercise.loggedSets.size
    this.setsCompleted = loggedExercise.loggedSets.count { it.completed }

    var completedFound = false
    this.sets.addAll(loggedExercise.loggedSets.map { set ->
      var current = false
      if (!completedFound) {
        current = !set.completed
        completedFound = !set.completed
      }

      LoggedSetViewModel(set.reps, set.weight, current)
    })
  }

}