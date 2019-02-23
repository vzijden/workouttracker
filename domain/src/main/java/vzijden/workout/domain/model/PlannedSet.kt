package vzijden.workout.domain.model


data class PlannedSet(

    var reps: Int,
    var index: Int,
    var registrationId: Long
) {

  var id: Long = 0
    private set

  constructor(reps: Int, index: Int, registrationId: Long, id: Long) : this(reps, index, registrationId) {
    this.id = id
  }
}
