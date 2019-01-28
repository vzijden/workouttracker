package vzijden.workout.data.mapper

import vzijden.workout.data.model.SchedulePojo
import vzijden.workout.domain.model.Schedule

fun mapScheduleToPojo(schedule: Schedule): SchedulePojo {
  return SchedulePojo(schedule.name, schedule.id)
}

fun mapScheduleToEntity(schedulePojo: SchedulePojo): Schedule {
  return Schedule(schedulePojo.name, schedulePojo.id)
}