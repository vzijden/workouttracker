package vzijden.workout.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vzijden.workout.data.dao.EquipmentDao
import vzijden.workout.data.dao.ExerciseDao
import vzijden.workout.data.dao.LogDao
import vzijden.workout.data.model.EquipmentPojo
import vzijden.workout.data.model.ExercisePojo
import vzijden.workout.data.model.LoggedSetPojo
import vzijden.workout.data.model.LoggedWorkoutPojo

@Database(entities = [ExercisePojo::class, EquipmentPojo::class, LoggedSetPojo::class, LoggedWorkoutPojo::class], version = 1)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun registrationDao(): LogDao
}