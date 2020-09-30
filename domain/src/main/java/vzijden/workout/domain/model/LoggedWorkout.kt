package vzijden.workout.domain.model

class LoggedWorkout constructor(val id: Long, val loggedExercises: List<LoggedExercise>, val currentLoggedSetId: Long)