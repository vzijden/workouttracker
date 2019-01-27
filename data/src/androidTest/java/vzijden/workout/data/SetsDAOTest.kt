package vzijden.workout.data


import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.Registration
import vzijden.workout.data.model.Set
import vzijden.workout.data.model.Workout

@RunWith(AndroidJUnit4::class)
class SetsDAOTest {
  private lateinit var workoutDatabase: WorkoutDatabase

  @Before
  fun setupDB() {
    workoutDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
        WorkoutDatabase::class.java)
        // allowing main thread queries, just for testing
        .allowMainThreadQueries()
        .build()

  }

  @Test
  fun testSetsDAO() {
    val workout = Workout(0, "test1", 0)
    val id = workoutDatabase.workoutDao().insert(workout)
    val exercise = Exercise("test", "")
    val exerciseId = workoutDatabase.exerciseDao().insert(exercise)
    exercise.id = exerciseId.toInt()
    val registration = Registration(id.toInt(), exercise)
    val registrationId = workoutDatabase.registrationDao().insert(registration)
    val set = Set(8, registrationId.toInt())
    val setId = workoutDatabase.setsDao().insert(set)

//    val setFlowable = workoutDatabase.setsDao().getById(setId.toInt())
//    val result = setFlowable.blockingFirst()
//    assertEquals(set.id, result.id)

  }
}