package vzijden.workout.view.schedule.workout

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.MuscleGroup
import vzijden.workout.data.model.WorkoutAndExercices

class EditWorkoutPresenter(private val scheduleDatabase: ScheduleDatabase, private val view: EditWorkoutPresenter.View) : BaseObservable() {
  private var workoutAndExercises: WorkoutAndExercices? = null
  private var workoutName = ObservableField<String>()

  fun loadWorkout(workoutId: Int) {
    doAsync {
      val byWorkoutId = scheduleDatabase.workoutAndExercisesDao().byWorkoutId(workoutId)
      byWorkoutId?.let {
        workoutAndExercises = it
        workoutName.set(it.workout.name)
        view.setWorkoutAndExercises(it)
      }
    }
  }

  @get:Bindable
  var exercises = mutableListOf<Exercise>()
    get() {
      return workoutAndExercises?.exercices ?: mutableListOf()
    }

  @get:Bindable
  var changedPositions = mutableSetOf<Int>()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }

  fun newWorkoutAndExercises(): WorkoutAndExercices {
    this.workoutAndExercises = WorkoutAndExercices()
    return this.workoutAndExercises!!
  }

  fun newExercise() {
    workoutAndExercises?.workout?.let { workout ->
      val exercise = Exercise("New exercise", workout.id, MuscleGroup.BICEPS)
      workoutAndExercises?.exercices?.add(exercise)
      view.addExercise(exercise)
      notifyPropertyChanged(BR.exercises)
      changedPositions = mutableSetOf(exercises.size - 1)
    }
  }

  public fun onExerciseClicked(view: View) {

  }

  interface View {
    fun setWorkoutAndExercises(workoutAndExercises: WorkoutAndExercices)

    fun addExercise(exercise: Exercise)

    fun openExercise(exercise: Exercise)
  }

  interface ExerciseItemView {
    fun setName(name: String)
    fun setMuscleType(muscleType: String)
    fun setIndex(index: String)
    fun setOnClickListener(): () -> Exercise
  }
}