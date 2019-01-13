package vzijden.workout.view.main


import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.ScheduleAndWorkouts
import vzijden.workout.data.model.Workout

class SchedulePresenter(val view: View, scheduleDatabase: ScheduleDatabase): BaseObservable() {
  var scheduleAndWorkout: ScheduleAndWorkouts? = null
  set(value) {
    field = value
    notifyPropertyChanged(BR.scheduleItemViews)
  }

  init {
    doAsync {
      val scheduleAndWorkouts = scheduleDatabase.scheduleAndWorkoutsDao().allSchedulesAndWorkouts()
      if (scheduleAndWorkouts.isNotEmpty()) {
        scheduleAndWorkout = scheduleAndWorkouts[0]
      }
    }
  }

  @get:Bindable
  var scheduleItemViews: List<ScheduleItemView> = listOf()
  get() {
    return scheduleAndWorkout?.workouts?.map { workout ->
      object: ScheduleItemView() {
        override fun onClick() {
          view.editWorkout(workout)
        }
        override val workoutName: String
          get() = workout.name
        override val workoutDay: Int
          get() = workout.day ?: -1
      }
    }  ?: emptyList()
  }

  @get:Bindable
  var changedPositions = mutableSetOf<Int>()
    set(value) {
      field = value
      notifyPropertyChanged(BR.changedPositions)
    }
  interface View {
    fun editWorkout(workout: Workout)
  }

  abstract class ScheduleItemView: BaseObservable() {
    @get:Bindable
    abstract val workoutName: String
    @get:Bindable
    abstract val workoutDay: Int
    abstract fun onClick()
  }
}