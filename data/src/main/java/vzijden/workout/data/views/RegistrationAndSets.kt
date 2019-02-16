package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.model.PlannedSetPojo

class RegistrationAndSets {
  @Embedded lateinit var plannedExercisePojo: PlannedExercisePojo
  @Relation(parentColumn = "id", entityColumn = "registrationId", entity = PlannedSetPojo::class)
  var plannedSetPojos: MutableList<PlannedSetPojo> = mutableListOf()
}