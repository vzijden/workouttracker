package vzijden.workout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.repository.WorkoutRepositoryImpl
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.CreatePlannedSet
import vzijden.workout.scheduler.Schedulers

@RunWith(AndroidJUnit4::class)
class TestWorkoutDatabase {
    private lateinit var workoutDatabase: WorkoutDatabase
    private lateinit var workoutRepository: WorkoutRepository
    private lateinit var createPlannedSet: CreatePlannedSet

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupDB() {
        workoutDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WorkoutDatabase::class.java)
        // allowing main thread queries, just for testing
        .allowMainThreadQueries()
                .build()

        workoutRepository = WorkoutRepositoryImpl(workoutDatabase, InstrumentationRegistry.getContext())

        val schedulers: Schedulers = object: Schedulers {
            override fun observeOn(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

            override fun subscribeOn(): Scheduler {
                return io.reactivex.schedulers.Schedulers.io()
            }

        }

        createPlannedSet = CreatePlannedSet(workoutRepository, schedulers.observeOn(), schedulers.subscribeOn())
    }

    @Test
    fun testAddSet() {
        val plannedSetId = createPlannedSet.build(1).blockingGet()
        Assert.assertNotEquals(0, plannedSetId)

    }
}
