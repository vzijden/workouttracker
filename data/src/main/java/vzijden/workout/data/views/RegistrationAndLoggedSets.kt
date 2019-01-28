package vzijden.workout.data.model

import androidx.room.Relation

class RegistrationAndLoggedSets {
  @Relation(parentColumn = "id", entityColumn = "set_registrationId", entity = LoggedSetPojo::class)
  var loggedSetPojos: MutableList<LoggedSetPojo> = mutableListOf()
}
