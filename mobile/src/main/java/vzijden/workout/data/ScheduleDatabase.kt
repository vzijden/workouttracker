package vzijden.workout.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vzijden.workout.data.dao.*
import vzijden.workout.data.model.Exercise
import vzijden.workout.data.model.Schedule
import vzijden.workout.data.model.Set
import vzijden.workout.data.model.Workout

@Database(entities = arrayOf(Schedule::class, Workout::class, Exercise::class, Set::class), version = 1)
@TypeConverters(Converters::class)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun workoutAndExercisesDao() : WorkoutAndExercisesDao
    abstract fun exerciseDao() : ExerciseDao
    abstract fun workoutDao() : WorkoutDao
    abstract fun scheduleAndWorkoutsDao(): ScheduleAndWorkoutsDao
    abstract fun setsDao(): SetsDao

    companion object {
        private var INSTANCE: ScheduleDatabase? = null
        fun getInstance(context: Context): ScheduleDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context,
                        ScheduleDatabase::class.java,
                        "roomdb")
                        .build()
            }
            return INSTANCE as ScheduleDatabase
        }
    }
}