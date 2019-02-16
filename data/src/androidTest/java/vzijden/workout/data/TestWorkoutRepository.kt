package vzijden.workout.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import vzijden.workout.data.model.*
import vzijden.workout.data.repository.WorkoutRepositoryImpl
import vzijden.workout.domain.repository.WorkoutRepository


@RunWith(AndroidJUnit4::class)
class TestWorkoutRepository {
  private lateinit var workoutDatabase: WorkoutDatabase
  private lateinit var workoutRepository: WorkoutRepository

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setupDB() {
    workoutDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
        WorkoutDatabase::class.java)
        // allowing main thread queries, just for testing
        .allowMainThreadQueries()
        .build()

    workoutRepository = WorkoutRepositoryImpl(workoutDatabase)
  }

  @Test
  fun testSetsDAO() {
    val workout = PlannedWorkoutPojo(0, "test1", 0)
    val workoutId = workoutDatabase.workoutDao().insert(workout)

    val workoutId1 = workoutId.blockingGet()
    val workoutObservable = workoutRepository.getPlannedWorkout(workoutId1)
    var registrationCount = 0;
    workoutObservable.observeOn(AndroidSchedulers.mainThread()).subscribe {
      Assert.assertEquals(registrationCount, it.plannedExercises.orEmpty().size)
    }

    val exercisePojo = ExercisePojo("test", "test", 0, listOf(MuscleGroupPojo.ABS))
    val plannedExercisePojo = PlannedExercisePojo(workoutId1, exercisePojo)
    val plannedExerciseId = workoutDatabase.registrationDao().insert(plannedExercisePojo)
    registrationCount++


    val plannedSet = PlannedSetPojo(8,plannedExerciseId.blockingGet())
    val plannedSetId = workoutDatabase.setsDao().insert(plannedSet).blockingGet()

    val set = workoutDatabase.setsDao().getById(plannedSetId.toInt()).blockingFirst()
    Assert.assertEquals(set.id, plannedSetId.toInt())
  }
}