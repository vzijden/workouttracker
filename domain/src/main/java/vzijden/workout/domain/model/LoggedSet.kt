package vzijden.workout.domain.model

data class LoggedSet(
    var weight: Int,
    var reps: Int
) {
  constructor(weight: Int, reps: Int, unit: Int, id: Int) : this(weight, reps) {
    this.unit = unit
    this.id = id
  }

  var unit: Int = 0
    private set
  var id: Int = 0
    private set

  var completed = false
}