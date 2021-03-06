package vzijden.workout.domain.model

data class LoggedSet(
    var reps: Int ,
    val exercise: Exercise
) {

  // Data constructor
  constructor(id: Long, reps: Int, exercise: Exercise, index: Int) : this(reps, exercise) {
    this.id = id
    this.index = index
  }

  var id: Long = 0
    private set

  var index = 0
    internal set

  var weight: Int = 0
    internal set

  var completed = false
    internal set

}