package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutAndHistory(
    @Embedded
    val workout: Workout,
    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = Registration::class)
    var registrationsAndSets: MutableList<RegistrationAndSets> = mutableListOf(),

    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = LoggedSet::class)
    var loggedSets: MutableList<RegistrationAndLoggedSets> = mutableListOf(),

    @Relation(parentColumn = "id", entityColumn = "workout_id", entity = LoggedWorkoutAndRegistrations::class)
    val workoutHistory: List<LoggedWorkoutAndRegistrations>
)
