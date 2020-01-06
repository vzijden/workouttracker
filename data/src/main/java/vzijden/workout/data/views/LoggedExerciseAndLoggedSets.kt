package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.domain.model.Exercise

class LoggedExerciseAndLoggedSets(@Embedded val loggedExercisePojo: LoggedExercisePojo) {
  @Relation(parentColumn = "exerciseId", entityColumn = "id", entity = Exercise::class)
  lateinit var exercise: Exercise
  @Relation(parentColumn = "id", entityColumn = "loggedExerciseId", entity = LoggedSetPojo::class)
  var loggedSetPojos: List<LoggedSetPojo> = listOf()
}
