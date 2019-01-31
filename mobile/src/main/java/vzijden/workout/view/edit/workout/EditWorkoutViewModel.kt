package vzijden.workout.view.edit.workout

import androidx.databinding.*
import vzijden.workout.databinding.OnItemClickedListener
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.GetWorkout
import vzijden.workout.domain.usecase.UpdateWorkout

class EditWorkoutViewModel(private val getWorkout: GetWorkout, private val updateWorkout: UpdateWorkout) : BaseObservable() {
  val workout = ObservableField<PlannedWorkout>()
  val exercises = ObservableArrayList<ExerciseItemViewHolder>()
  var exercisesFragmentView: ExercisesFragmentView? = null

  @get:Bindable
  val onItemDeletedListener = object : OnItemClickedListener<ExerciseItemViewHolder> {
    override fun onItemClicked(item: ExerciseItemViewHolder, pos: Int) {

    }

  }

  fun loadWorkout(workoutId: Int) {
    getWorkout.build(workoutId).subscribe {
      workout.set(it)
      it.plannedExercises.forEachIndexed(::addExerciseViewHolder)
    }
  }

  fun newWorkout() {

  }

  fun selectExercise(exerciseId: Int) {

  }

  fun onAddExerciseClick() {

  }

  private fun addExerciseViewHolder(index: Int, plannedExercise: PlannedExercise) {
    val itemViewHolder = object : ExerciseItemViewHolder() {
      override val exerciseName: String
        get() = plannedExercise.exercise.name

      override
      val index: Int
        get() = index

      override
      val muscleGroup: String
        get() = plannedExercise.exercise.muscleGroups.firstOrNull()?.normalName ?: ""

      override
      val sets: ObservableArrayList<PlannedSet>
        get() = ObservableArrayList<PlannedSet>().apply {
          addAll(plannedExercise.plannedSets.orEmpty())
        }

      override
      fun onClick() {
      }


      override fun onAddItemClick() {
      }

    }

    exercises.add(itemViewHolder)
  }

  private fun onExerciseClicked(pos: Int) {

  }

  interface ExercisesFragmentView {
    fun openRegistration(plannedExercise: PlannedExercise, workoutId: Int)
    fun newRegistration(workoutId: Int)
  }

  abstract class ExerciseItemViewHolder : BaseObservable() {
    @get:Bindable
    abstract val exerciseName: String
    @get:Bindable
    abstract val index: Int
    @get:Bindable
    abstract val muscleGroup: String
    @get:Bindable
    abstract val sets: ObservableArrayList<PlannedSet>

    abstract fun onClick()
    abstract fun onAddItemClick()
  };

}