package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class RegistrationAndSets(@Embedded var registration: Registration) {
  @Relation(parentColumn = "id", entityColumn = "registrationId", entity = Set::class)
  var sets: MutableList<vzijden.workout.data.model.Set> = mutableListOf()
}