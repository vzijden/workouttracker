package vzijden.workout.view.exercises

import android.widget.SearchView
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import vzijden.workout.databinding.OnItemClickedListener
import vzijden.workout.domain.model.Exercise

class SelectExercisePresenter(val view: SelectExerciseView) : BaseObservable() {
  val exercises = ObservableArrayList<Exercise>()
  val filteredExercises: MutableList<Exercise> = mutableListOf()
  val allExercises: MutableList<Exercise> = mutableListOf()
  val onItemClickedListener = object : OnItemClickedListener<Exercise> {
    override fun onItemClicked(item: Exercise, pos: Int) {
      view.selectExercise(item)
    }
  }

  //  @get:Bindable
  val onQueryTextListener = object : SearchView.OnQueryTextListener {
    var query = ""
    override fun onQueryTextSubmit(query: String?): Boolean {
      if (query != null)
        this.query = query
      return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
      synchronized(this) {
        if (newText != null && newText.isNotEmpty()) {
          if (newText.length < query.length) {
            allExercises.forEach { exercise ->
              if (exercise.name.contains(newText) && filteredExercises.remove(exercise)) {
                exercises.add(exercise)
              }
              exercises.sortBy { it.name }
            }
          } else {
            allExercises.forEach { exercise ->
              if (!exercise.name.contains(newText) && exercises.remove(exercise)) {
                filteredExercises.add(exercise)
              }
            }
          }
          query = newText
          return true
        }
        exercises.clear()
        exercises.addAll(allExercises)
        return false
      }
    }

  }

  init {

  }

  interface SelectExerciseView {
    fun selectExercise(exercise: Exercise)
    fun noneSelected()
  }
}