package vzijden.workout.domain.model

data class Schedule(val name: String) {
  constructor(name:String, id: Int) : this(name) {
    this.id = id
  }
  var id: Int = 0
}