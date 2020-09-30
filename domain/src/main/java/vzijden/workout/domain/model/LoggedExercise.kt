package vzijden.workout.domain.model

data class LoggedExercise internal constructor(val exercise: Exercise, val loggedSets: List<LoggedSet>)
