package vzijden.workout.view.home


import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import org.jetbrains.anko.doAsync
import vzijden.workout.BR
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.ScheduleAndWorkouts
import vzijden.workout.data.model.Workout

class SchedulePresenter(val view: View, scheduleDatabase: ScheduleDatabase) : BaseObservable() {
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
        scheduleAndWorkouts[0].workouts.forEachIndexed { index, workout ->
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
    fun editWorkout(workout: Workout)
    fun startWorkout(workout: Workout)
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