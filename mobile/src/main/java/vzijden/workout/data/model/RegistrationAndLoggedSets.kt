package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class RegistrationAndLoggedSets(
    @Embedded
    val registration: Registration,
    @Relation(parentColumn = "id", entityColumn = "set_registrationId", entity = LoggedSet::class)
    val loggedSets: MutableList<LoggedSet>
)
