package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

class RegistrationAndLoggedSets(
    @Embedded
    public var registration: Registration) {
  @Relation(parentColumn = "id", entityColumn = "set_registrationId", entity = LoggedSet::class)
  var loggedSets: MutableList<LoggedSet> = mutableListOf()
}
