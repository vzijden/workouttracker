package vzijden.workout.dagger.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.repository.WorkoutRepositoryImpl
import vzijden.workout.domain.repository.WorkoutRepository
import javax.inject.Singleton

@Module
class DataBaseModule {
  @Provides
  @Singleton
  internal fun provideDataBase(context: Context): WorkoutDatabase {
    return Room.databaseBuilder(
        context,
        WorkoutDatabase::class.java,
        "roomdb")
        .build()
  }

  @Provides
  @Singleton
  internal fun providesWorkoutRepository(workoutDatabase: WorkoutDatabase, context: Context): WorkoutRepository =
      WorkoutRepositoryImpl(workoutDatabase, context)
}