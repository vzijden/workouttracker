package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.*

data class WorkoutAndHistory(
    @Embedded
    val workout: Workout,
    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = Registration::class)
    var registrationsAndSets: MutableList<RegistrationAndSets> = mutableListOf(),

    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = Registration::class)
    var loggedSets: MutableList<RegistrationAndLoggedSets> = mutableListOf(),

    @Relation(parentColumn = "id", entityColumn = "workout_id", entity = LoggedWorkout::class)
    val workoutHistory: List<LoggedWorkoutAndRegistrations>
)
