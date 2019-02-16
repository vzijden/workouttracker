package vzijden.workout.view.edit.workout.exercise

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import io.reactivex.rxkotlin.subscribeBy
import vzijden.workout.domain.model.PlannedExercise
import vzijden.workout.domain.model.PlannedSet
import vzijden.workout.domain.usecase.CreatePlannedSet
import vzijden.workout.domain.usecase.DeletePlannedSet
import vzijden.workout.view.edit.workout.exercise.set.SetItemViewModel

abstract class ExerciseItemViewModel(val plannedExercise: PlannedExercise, val index: Int) : BaseObservable() {

  val name = ObservableField<String>()
  val muscleGroup = ObservableField<String>()
  val plannedSets: ObservableArrayList<PlannedSet> = ObservableArrayList()

  init {
    name.set(plannedExercise.exercise.name)
    muscleGroup.set(plannedExercise.exercise.muscleGroups.firstOrNull()?.normalName ?: "")
  }


  abstract fun onClick()

  abstract fun onAddItemClick()
  // {
//    plannedExercise?.let { plannedExercise ->
//      val plannedSet = PlannedSet(8, plannedExercise.id.toLong())
//      createPlannedSet.execute(plannedSet).subscribeBy(
//          onSuccess = {
//            plannedSets.add(plannedSet)
//          },
//          onError = { error: Throwable ->
//            error.printStackTrace()
//          }
//      )
//    }
//
//  }
//
  abstract fun onDeleteClick(setId: Int)
//{
//    deletePlannedSet.execute().subscribe {
//      plannedSets.removeIf { it.id.toInt() == setId }
//    }
//  }
}
