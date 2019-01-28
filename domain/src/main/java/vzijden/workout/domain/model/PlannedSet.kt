package vzijden.workout.domain.model


data class PlannedSet(

    var reps: Int,
    var registrationId: Int
) {

  var id: Int = 0
    private set

  constructor(reps: Int, registrationId: Int, id: Int) : this(reps, registrationId) {
    this.id = id
  }
}
