package vzijden.workout.data

import androidx.room.TypeConverter
import vzijden.workout.data.model.MuscleGroup
import java.util.*

class Converters {
  @TypeConverter
  fun fromTimestamp(value: Long?): Date? {
    return value?.let { Date(it) }
  }

  @TypeConverter
  fun toTimestamp(value: Date?): Long? {
    return value?.time
  }

  @TypeConverter
  fun toMuscleGroup(value: String?): MuscleGroup? {
    return value?.let { MuscleGroup.valueOf(it) }
  }

  @TypeConverter
  fun fromMuscleGroup(value: MuscleGroup?): String? {
    return value?.let { it.name }
  }
}