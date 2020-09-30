package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.LoggedWorkoutPojo

class LoggedWorkoutWithSets(@Embedded var loggedWorkoutPojo: LoggedWorkoutPojo) {
  @Relation(parentColumn = "id", entityColumn = "workoutId")
  var loggedSets: List<LoggedSetPojo> = emptyList()
}