package vzijden.workout.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.data.model.MuscleGroupPojo
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.repository.WorkoutRepositoryImpl
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.GetCurrentWorkout


@RunWith(AndroidJUnit4::class)
class TestWorkoutRepository {
  private lateinit var workoutDatabase: WorkoutDatabase
  private lateinit var workoutRepository: WorkoutRepository

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setupDB() {
    val instrumentationContext = InstrumentationRegistry.getInstrumentation().context

    workoutDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
        WorkoutDatabase::class.java)
        // allowing main thread queries, just for testing
        .allowMainThreadQueries()
        .build()

    workoutRepository = WorkoutRepositoryImpl(workoutDatabase, instrumentationContext)
  }

  @Test
  fun testSetsDAO() {
    val workout = PlannedWorkoutPojo(0, "test1", 0)
    val workoutId = workoutDatabase.workoutDao().insert(workout)

    val workoutId1 = workoutId.blockingGet()
    val workoutObservable = workoutRepository.getPlannedWorkout(workoutId1)
    var registrationCount = 0;
    workoutObservable.observeOn(AndroidSchedulers.mainThread()).subscribe {
      Assert.assertEquals(registrationCount, it.plannedExercises.size)
    }

    val exercisePojo = ExercisePojo("test", "test", 0, listOf(MuscleGroupPojo.ABS))
    val plannedExercisePojo = PlannedExercisePojo(workoutId1, exercisePojo, 0)
    val plannedExerciseId = workoutDatabase.registrationDao().insert(plannedExercisePojo)
    registrationCount++


    val plannedSet = PlannedSetPojo(8, 0, plannedExerciseId.blockingGet())
    val plannedSetId = workoutDatabase.setsDao().insert(plannedSet).blockingGet()

    val set = workoutDatabase.setsDao().getById(plannedSetId).blockingFirst()
    Assert.assertSame(set.id, plannedSetId)
  }

  @Test
  fun testSaveLoggedWorkout() {
    val testScheduler = TestScheduler()

    val workout = PlannedWorkoutPojo(0, "test1", 0)
    val workoutId = workoutDatabase.workoutDao().insert(workout)

    val workoutId1 = workoutId.blockingGet()
    val workoutObservable = workoutRepository.getPlannedWorkout(workoutId1)
    var registrationCount = 0;
    workoutObservable.observeOn(AndroidSchedulers.mainThread()).subscribe {
      Assert.assertEquals(registrationCount, it.plannedExercises.size)
    }

    val exercisePojo = ExercisePojo("test", "test", 0, listOf(MuscleGroupPojo.ABS))
    val plannedExercisePojo = PlannedExercisePojo(workoutId1, exercisePojo, 0)
    val plannedExerciseId = workoutDatabase.registrationDao().insert(plannedExercisePojo)
    registrationCount++


    val plannedSet = PlannedSetPojo(8, 0, plannedExerciseId.blockingGet())
    workoutDatabase.setsDao().insert(plannedSet).blockingGet()

    val getCurrentWorkout = GetCurrentWorkout(workoutRepository, testScheduler, testScheduler)
    var result = getCurrentWorkout.build(workoutId1).blockingFirst()
    assertThat(result.loggedExercises, hasSize(1))

    // Make sure it returns the same logged workout on a second getCurrentWorkout()
    result = getCurrentWorkout.build(workoutId1).blockingFirst()
    assertThat(result.loggedExercises, hasSize(1))
  }

}