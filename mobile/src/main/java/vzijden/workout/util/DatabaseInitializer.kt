package vzijden.workout.util

import android.content.Context
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import org.json.JSONObject
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Equipment
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.MuscleGroup


public fun initialize(context: Context) {
  val scheduleDatabase = ScheduleDatabase.getInstance(context)

  val exercisesInput = context.resources.openRawResource(R.raw.exercises)
  val jsonArray = JSONArray(IOUtils.toString(exercisesInput))
  for (i in 0 until jsonArray.length() ) {
    val exercise = readExercise(jsonArray.getJSONObject(i))
    exercise?.let(scheduleDatabase.exerciseDao()::insert)
  }
}

internal fun readExercise(jsonObject: JSONObject): Exercise? {
  val fields = jsonObject.getJSONObject("fields")
  if (!fields.has("language") || fields.getInt("language") != 2) {
    return null
  }

  val name = fields.getString("name")
  val musclesJsonArray = fields.getJSONArray("muscles")
  val muscles = mutableListOf<MuscleGroup>()
  for (i in 0 until musclesJsonArray.length()) {
    when (musclesJsonArray.get(i)) {
      2 -> muscles.add(MuscleGroup.SHOULDERS) //Anterior deltoid
      1 -> muscles.add(MuscleGroup.BICEPS) //Biceps brachii
      11 -> muscles.add(MuscleGroup.LEGS) //Biceps femoris
      13 -> muscles.add(MuscleGroup.BICEPS) //Brachialis
      16 -> muscles.add(MuscleGroup.BACK) //Erector spinae
      7 -> muscles.add(MuscleGroup.LEGS) //Gastrocnemius
      8 -> muscles.add(MuscleGroup.LEGS) //Gluteus maximus
      12 -> muscles.add(MuscleGroup.BACK) //Latissimus dorsi
      14 -> muscles.add(MuscleGroup.ABS) //Obliquus externus abdominis
      4 -> muscles.add(MuscleGroup.BACK) //Pectoralis major
      10 -> muscles.add(MuscleGroup.TRICEPS) //Quadriceps femoris
      6 -> muscles.add(MuscleGroup.ABS) //Rectus abdominis
      3 -> muscles.add(MuscleGroup.ABS) //Serratus anterior
      15 -> muscles.add(MuscleGroup.LEGS) //Soleus
      9 -> muscles.add(MuscleGroup.TRAPS) //Trapezius
      5 -> muscles.add(MuscleGroup.TRICEPS) //Triceps brachii

    }
  }

  val description = fields.getString("description")

  val exercise = Exercise(name, description)
  exercise.muscleGroups = muscles
  return exercise
}

private fun readMusleGroup(jsonObject: JSONObject): MuscleGroup? {
  return null
}

private fun readEquipment(jsonObject: JSONObject): Equipment? {
  return null
}


