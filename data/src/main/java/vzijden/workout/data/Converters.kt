package vzijden.workout.data

import android.util.Log
import androidx.room.TypeConverter
import vzijden.workout.data.model.MuscleGroupPojo
import java.lang.IllegalArgumentException
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
  fun toMuscleGroup(value: String): List<MuscleGroupPojo>? {
    return value.split(",").map {
      try {
        MuscleGroupPojo.valueOf(it)
      } catch (e: IllegalArgumentException) {
        Log.e(Converters::class.simpleName, "Uknown musclegroup $it")
        return null
      }
    }
  }

  @TypeConverter
  fun fromMuscleGroup(value: List<MuscleGroupPojo>): String? {
    return value.joinToString(separator = ",") { it.name }
  }
}