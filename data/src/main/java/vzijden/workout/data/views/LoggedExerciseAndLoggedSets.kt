package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.PlannedSetPojo

class LoggedExerciseAndLoggedSets(@Embedded val loggedExercisePojo: LoggedExercisePojo) {
  @Relation(parentColumn = "plannedExercise_id", entityColumn = "registrationId", entity = PlannedSetPojo::class)
  var plannedSetPojos: List<PlannedSetPojo> = listOf()
  @Relation(parentColumn = "id", entityColumn = "loggedExerciseId", entity = LoggedSetPojo::class)
  var loggedSetPojos: List<LoggedSetPojo> = listOf()
}
