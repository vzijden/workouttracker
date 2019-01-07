package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class ScheduleAndWorkouts {
  @Embedded
  lateinit var schedule: Schedule
  @Relation(parentColumn = "id", entityColumn = "scheduleId", entity = Workout::class)
  lateinit var workouts: List<Workout>
}
