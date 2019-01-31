package vzijden.workout.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import vzijden.workout.App
import javax.inject.Singleton

@Module
class AppModule {
  @Provides
  @Singleton
  fun provideContext(application: App): Context = application.applicationContext

}