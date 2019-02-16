package vzijden.workout.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vzijden.workout.data.dao.*
import vzijden.workout.data.model.*
import vzijden.workout.data.model.PlannedSetPojo

@Database(entities = [SchedulePojo::class, PlannedWorkoutPojo::class, ExercisePojo::class, PlannedSetPojo::class, EquipmentPojo::class, PlannedExercisePojo::class, LoggedWorkoutPojo::class, LoggedSetPojo::class, LoggedExercisePojo::class], version = 1)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun workoutAndRegistrationsDao() : WorkoutAndRegistrationsDao
    abstract fun exerciseDao() : ExerciseDao
    abstract fun workoutDao() : WorkoutDao
    abstract fun scheduleAndWorkoutsDao(): ScheduleAndWorkoutsDao
    abstract fun setsDao(): RegistrationSetsDao
    abstract fun registrationAndSetsDao(): RegistrationAndSetsDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun registrationDao(): RegistrationDao
}