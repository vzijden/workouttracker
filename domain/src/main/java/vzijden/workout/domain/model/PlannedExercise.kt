package vzijden.workout.domain.model


class PlannedExercise(
    val workoutId: Int,
    val exercise: Exercise,
    val index: Int
) {
  constructor(workoutId: Int, exercise: Exercise, id: Int, plannedSets: List<PlannedSet>, index: Int) : this(workoutId, exercise, index) {
    this.id = id
    this.plannedSets = plannedSets
  }

  var plannedSets: List<PlannedSet> = emptyList()
  private set
  var id: Int = 0
  private set
}