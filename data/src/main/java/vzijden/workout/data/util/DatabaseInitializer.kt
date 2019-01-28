package vzijden.workout.data.util

import org.apache.commons.io.IOUtils
import org.json.JSONArray
import org.json.JSONObject
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.data.model.MuscleGroupPojo
import vzijden.workout.domain.model.Equipment
import java.io.InputStream


public fun initialize(workoutDatabase: WorkoutDatabase, exercisesInput: InputStream) {
  val jsonArray = JSONArray(IOUtils.toString(exercisesInput))
  for (i in 0 until jsonArray.length() ) {
    val exercise = readExercise(jsonArray.getJSONObject(i))
    exercise?.let(workoutDatabase.exerciseDao()::insert)
  }
}

internal fun readExercise(jsonObject: JSONObject): ExercisePojo? {
  val fields = jsonObject.getJSONObject("fields")
  if (!fields.has("language") || fields.getInt("language") != 2) {
    return null
  }

  val name = fields.getString("name")
  val musclesJsonArray = fields.getJSONArray("muscles")
  val muscles = mutableListOf<MuscleGroupPojo>()
  for (i in 0 until musclesJsonArray.length()) {
    when (musclesJsonArray.get(i)) {
      2 -> muscles.add(MuscleGroupPojo.SHOULDERS) //Anterior deltoid
      1 -> muscles.add(MuscleGroupPojo.BICEPS) //Biceps brachii
      11 -> muscles.add(MuscleGroupPojo.LEGS) //Biceps femoris
      13 -> muscles.add(MuscleGroupPojo.BICEPS) //Brachialis
      16 -> muscles.add(MuscleGroupPojo.BACK) //Erector spinae
      7 -> muscles.add(MuscleGroupPojo.LEGS) //Gastrocnemius
      8 -> muscles.add(MuscleGroupPojo.LEGS) //Gluteus maximus
      12 -> muscles.add(MuscleGroupPojo.BACK) //Latissimus dorsi
      14 -> muscles.add(MuscleGroupPojo.ABS) //Obliquus externus abdominis
      4 -> muscles.add(MuscleGroupPojo.BACK) //Pectoralis major
      10 -> muscles.add(MuscleGroupPojo.TRICEPS) //Quadriceps femoris
      6 -> muscles.add(MuscleGroupPojo.ABS) //Rectus abdominis
      3 -> muscles.add(MuscleGroupPojo.ABS) //Serratus anterior
      15 -> muscles.add(MuscleGroupPojo.LEGS) //Soleus
      9 -> muscles.add(MuscleGroupPojo.TRAPS) //Trapezius
      5 -> muscles.add(MuscleGroupPojo.TRICEPS) //Triceps brachii

    }
  }

  val description = fields.getString("description")

  val exercise = ExercisePojo(name, description, muscles)
  return exercise
}

private fun readMusleGroup(jsonObject: JSONObject): MuscleGroupPojo? {
  return null
}

private fun readEquipment(jsonObject: JSONObject): Equipment? {
  return null
}


