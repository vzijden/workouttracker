package vzijden.workout.view.home.schedule


import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.domain.model.PlannedWorkout
import vzijden.workout.domain.usecase.GetPlannedWorkouts

class SchedulePresenter(val view: View, getPlannedWorkouts: GetPlannedWorkouts) : BaseObservable() {
  init {
    doAsync {
      getPlannedWorkouts.build(1).blockingFirst().forEachIndexed { index, workout ->
        scheduleItemViews.add(object : ScheduleItemView() {
          override fun onClick() {
            view.editWorkout(workout)
          }

          override fun onStartClick() {
            view.startWorkout(workout)
          }

          override val workoutName: String
            get() = workout.name
          override val workoutDay: Int
            get() = workout.day ?: -1
        })
      }
    }
  }

  @get:Bindable
  var scheduleItemViews: ObservableArrayList<ScheduleItemView> = ObservableArrayList()

  @get:Bindable
  var changedPositions = mutableSetOf<Int>()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }

  interface View {
    fun editWorkout(workout: PlannedWorkout)
    fun startWorkout(workout: PlannedWorkout)
  }

  abstract class ScheduleItemView : BaseObservable() {
    @get:Bindable
    abstract val workoutName: String
    @get:Bindable
    abstract val workoutDay: Int

    abstract fun onClick()
    abstract fun onStartClick()
  }
}