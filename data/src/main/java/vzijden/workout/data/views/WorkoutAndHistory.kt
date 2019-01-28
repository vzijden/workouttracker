package vzijden.workout.data.views

import androidx.room.Embedded
import androidx.room.Relation
import vzijden.workout.data.model.*

data class WorkoutAndHistory(
    @Embedded
    var workoutAndRegistrations: WorkoutAndRegistrations,

    @Relation(parentColumn = "id", entityColumn = "plannedExercise_workoutId", entity = LoggedExercisePojo::class)
    var loggedSets: MutableList<LoggedExerciseAndLoggedSets> = mutableListOf()
)
