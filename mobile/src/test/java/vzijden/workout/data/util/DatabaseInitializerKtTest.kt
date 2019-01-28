package vzijden.workout.data.util

import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import vzijden.workout.data.model.MuscleGroup

internal class DatabaseInitializerKtTest {

  @BeforeEach
  fun setUp() {
  }


  @Test
  fun readExerciseTest() {
    val exerciseJSON = JSONObject("  {\n" +
        "    \"pk\": 138,\n" +
        "    \"fields\": {\n" +
        "      \"license_author\": \"wger.de\",\n" +
        "      \"status\": \"2\",\n" +
        "      \"language\": 2,\n" +
        "      \"muscles_secondary\": [\n" +
        "        13\n" +
        "      ],\n" +
        "      \"description\": \"<p>Take a cable in your hands (palms parallel, point to each other), the body is straight. Bend the arms and bring the weight up with a fast movement. Without any pause bring it back down with a slow, controlled movement, but don't stretch completely your arms.</p>\\n<p>Don't swing your body during the exercise, the biceps should do all the work here. The elbows are at your side and don't move.</p>\",\n" +
        "      \"equipment\": [],\n" +
        "      \"creation_date\": \"2013-05-05\",\n" +
        "      \"category\": 8,\n" +
        "      \"uuid\": \"5baf40e5-ea3c-4f8d-b60a-d294ee2de55b\",\n" +
        "      \"muscles\": [\n" +
        "        1\n" +
        "      ],\n" +
        "      \"license\": 1,\n" +
        "      \"name\": \"Hammercurls on cable\"\n" +
        "    },\n" +
        "    \"model\": \"registrationsAndSets.exercise\"\n" +
        "  }")

    val exercise = readExercise(exerciseJSON)
    assertEquals("Hammercurls on cable", exercise!!.name)
    assertEquals(MuscleGroup.BICEPS, exercise.muscleGroups!![0])
  }
}