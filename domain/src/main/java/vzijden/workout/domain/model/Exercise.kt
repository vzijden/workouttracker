package vzijden.workout.domain.model

data class Exercise(
    var name: String,
    var description: String
) {
  constructor(name: String, description: String, id: Int, muscleGroups: List<MuscleGroup>): this(name, description) {
    this.id = id
    this.muscleGroups = muscleGroups
  }
  var id: Int = 0
  var muscleGroups: List<MuscleGroup> = mutableListOf()
}