package vzijden.workout.domain.model


data class PlannedSet(
    var reps: Int,
    var index: Int
) {

  var id: Int = 0
    private set

  constructor(reps: Int, index: Int, id: Int) : this(reps, index) {
    this.id = id
  }
}
