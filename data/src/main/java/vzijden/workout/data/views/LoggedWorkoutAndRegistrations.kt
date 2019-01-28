package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.LoggedExercisePojo
import vzijden.workout.data.model.LoggedWorkoutPojo
import vzijden.workout.data.model.PlannedExercisePojo

data class LoggedWorkoutAndRegistrations(
    @Embedded
    val loggedWorkoutPojo: LoggedWorkoutPojo
){
    @Relation(parentColumn = "id", entityColumn = "plannedExercise_workoutId", entity = LoggedExercisePojo::class)
    var loggedExercises: List<LoggedExerciseAndLoggedSets> = listOf()
}