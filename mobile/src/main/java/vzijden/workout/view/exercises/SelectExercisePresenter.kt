package vzijden.workout.view.exercises

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.databinding.OnItemClickedListener

class SelectExercisePresenter(val view: SelectExerciseView): BaseObservable() {
  val exercises = ObservableArrayList<Exercise>()
  val onItemClickedListener = object: OnItemClickedListener<Exercise> {
    override fun onItemClicked(item: Exercise, pos: Int) {
      view.selectExercise(item)
    }

  }

  init {
    doAsync {
      val all = view.getDatabase().exerciseDao().getAll()
      uiThread {
        exercises.addAll(all)
      }
    }
  }

  interface SelectExerciseView {
    fun getDatabase(): ScheduleDatabase
    fun selectExercise(exercise: Exercise)
    fun noneSelected()
  }
}