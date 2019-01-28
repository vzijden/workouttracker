package vzijden.workout.domain.model


class PlannedExercise(
    val workoutId: Int,
    val exercise: Exercise
) {
  constructor(workoutId: Int, exercise: Exercise, id: Int, plannedSets: List<PlannedSet>) : this(workoutId, exercise) {
    this.id = id
    this.plannedSets = plannedSets
  }

  var plannedSets: List<PlannedSet>? = null
  private set
  var id: Int = 0
  private set
}