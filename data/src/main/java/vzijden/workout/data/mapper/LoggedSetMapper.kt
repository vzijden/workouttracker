package vzijden.workout.data.mapper

import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.domain.model.LoggedSet

fun mapLoggedSetToPojo(entity: LoggedSet, loggedExerciseId: Int): LoggedSetPojo {
  return LoggedSetPojo(entity.weight, entity.reps, entity.unit, loggedExerciseId)
}

 fun mapLoggedSetToEntity(pojo: LoggedSetPojo): LoggedSet {
   return LoggedSet(pojo.weight, pojo.reps, pojo.unit, pojo.id)
}

