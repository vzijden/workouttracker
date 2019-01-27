package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.Registration
import vzijden.workout.data.model.Set

class RegistrationAndSets(@Embedded var registration: Registration) {
  @Relation(parentColumn = "id", entityColumn = "registrationId", entity = Set::class)
  var sets: MutableList<Set> = mutableListOf()
}