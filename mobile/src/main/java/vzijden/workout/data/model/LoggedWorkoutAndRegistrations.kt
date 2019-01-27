package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class LoggedWorkoutAndRegistrations(
    @Embedded
    val workout: LoggedWorkout
){
    @Relation(parentColumn = "id", entityColumn = "workoutId")
    val registrations: List<RegistrationAndLoggedSets> = listOf()
}