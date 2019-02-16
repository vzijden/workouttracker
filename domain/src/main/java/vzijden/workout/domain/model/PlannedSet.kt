package vzijden.workout.domain.model


data class PlannedSet(

    var reps: Int,
    var registrationId: Long
) {

  var id: Long = 0
    private set

  constructor(reps: Int, registrationId: Long, id: Long) : this(reps, registrationId) {
    this.id = id
  }
}
