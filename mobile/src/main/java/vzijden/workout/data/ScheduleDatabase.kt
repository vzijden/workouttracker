package vzijden.workout.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vzijden.workout.data.dao.*
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set

@Database(entities = [Schedule::class, Workout::class, Exercise::class, Set::class, Equipment::class, Registration::class, LoggedWorkout::class, LoggedSet::class, LoggedRegistration::class], version = 1)
@TypeConverters(Converters::class)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun workoutAndRegistrationsDao() : WorkoutAndRegistrationsDao
    abstract fun exerciseDao() : ExerciseDao
    abstract fun workoutDao() : WorkoutDao
    abstract fun scheduleAndWorkoutsDao(): ScheduleAndWorkoutsDao
    abstract fun setsDao(): SetsDao
    abstract fun registrationAndSetsDao(): RegistrationAndSetsDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun registrationDao(): RegistrationDao

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