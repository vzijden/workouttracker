package vzijden.workout.domain.model


class PlannedExercise(
    val workoutId: Long,
    val exercise: Exercise
) {
  constructor(workoutId: Long, exercise: Exercise, id: Long, plannedSets: List<PlannedSet>?) : this(workoutId, exercise) {
    this.id = id
    this.plannedSets = plannedSets ?: emptyList()
  }

  var plannedSets: List<PlannedSet> = emptyList()
  private set
  var id: Long = 0
  private set
}