package vzijden.workout.view.main


import org.jetbrains.anko.doAsync
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.ScheduleAndWorkouts

class WorkoutsPresenter(view: View, scheduleDatabase: ScheduleDatabase) {
  init {

    doAsync {
      val scheduleAndWorkouts = scheduleDatabase.scheduleAndWorkoutsDao().allSchedulesAndWorkouts()
      if (scheduleAndWorkouts.isNotEmpty()) {
        view.setSchedule(scheduleAndWorkouts[0])
      }
    }
  }

  interface View {
    fun setSchedule(schedule: ScheduleAndWorkouts)

  }
}