package vzijden.workout.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.data.model.PlannedWorkoutPojo
import org.junit.Rule
import vzijden.workout.data.views.RegistrationAndSets


@RunWith(AndroidJUnit4::class)
class SetsDAOTest {
  private lateinit var workoutDatabase: WorkoutDatabase

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

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
    val workout = PlannedWorkoutPojo(0, "test1", 0)
    val id = workoutDatabase.workoutDao().insert(workout)
    val exercise = ExercisePojo("test", "")
    val exerciseId = workoutDatabase.exerciseDao().insert(exercise)
    exercise.id = exerciseId.toInt()
    val registration = PlannedExercisePojo(id.toInt(), exercise)
    val registrationId = workoutDatabase.registrationDao().insert(registration)
    val set = PlannedSetPojo(8, registrationId.toInt())
    val setId = workoutDatabase.setsDao().insert(set)

    var assertId = 8

    val liveData: MutableLiveData<RegistrationAndSets> = MutableLiveData()
    workoutDatabase.registrationAndSetsDao().getAllForWorkout(id.toInt())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          liveData.postValue(it)
        }

    set.reps = 10
    set.id = setId.toInt()
    val completable = workoutDatabase.setsDao().update(set)
    assertId = 10
    completable.blockingAwait()
  }
}