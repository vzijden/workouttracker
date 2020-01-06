package vzijden.workout.domain.model

data class Exercise(
    var name: String,
    var description: String
) {
  constructor(name: String, description: String, id: Long, muscleGroups: List<MuscleGroup>) : this(name, description) {
    this.id = id
    this.muscleGroups = muscleGroups
  }

  var id: Long = 0
  var muscleGroups: List<MuscleGroup> = mutableListOf()
}