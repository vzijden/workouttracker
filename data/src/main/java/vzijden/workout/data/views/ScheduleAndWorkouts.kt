package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.SchedulePojo
import vzijden.workout.data.model.PlannedWorkoutPojo

class ScheduleAndWorkouts {
  @Embedded
  lateinit var schedulePojo: SchedulePojo
  @Relation(parentColumn = "id", entityColumn = "scheduleId", entity = PlannedWorkoutPojo::class)
  lateinit var plannedWorkoutPojos: List<PlannedWorkoutPojo>
}
