package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.Schedule
import vzijden.workout.data.model.Workout

class ScheduleAndWorkouts {
  @Embedded
  lateinit var schedule: Schedule
  @Relation(parentColumn = "id", entityColumn = "scheduleId", entity = Workout::class)
  lateinit var workouts: List<Workout>
}
