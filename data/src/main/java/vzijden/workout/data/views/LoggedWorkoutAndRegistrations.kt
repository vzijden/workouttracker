package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class LoggedWorkoutAndRegistrations(
    @Embedded
    val workoutPojo: LoggedWorkoutPojo
){
    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = PlannedExercisePojo::class)
    var registrations: List<RegistrationAndLoggedSets> = listOf()
}