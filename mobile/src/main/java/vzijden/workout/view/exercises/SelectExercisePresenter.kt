package vzijden.workout.view.exercises

import android.widget.SearchView
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.databinding.OnItemClickedListener

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
              if (exercise.name?.contains(newText) == true && filteredExercises.remove(exercise)) {
                exercises.add(exercise)
              }
              exercises.sortBy { it.name }
            }
          } else {
            allExercises.forEach { exercise ->
              if (exercise.name?.contains(newText) == false && exercises.remove(exercise)) {
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
    doAsync {
      val all = view.getDatabase().exerciseDao().getAll()
      uiThread {
        exercises.addAll(all)
        allExercises.addAll(all)
      }
    }
  }

  interface SelectExerciseView {
    fun getDatabase(): ScheduleDatabase
    fun selectExercise(exercise: Exercise)
    fun noneSelected()
  }
}