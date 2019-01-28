package vzijden.workout.data.mapper

import vzijden.workout.data.model.EquipmentPojo
import vzijden.workout.domain.model.Equipment

fun mapToPojo(entity: Equipment): EquipmentPojo {
  return EquipmentPojo(entity.name, entity.id)
}

fun mapToEntity(pojo: EquipmentPojo): Equipment {
  return Equipment(pojo.name, pojo.id)
}
