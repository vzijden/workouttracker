package vzijden.workout.dagger.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import vzijden.workout.scheduler.Schedulers
import javax.inject.Singleton

@Module
class SchedulerModule {
  @Provides
  @Singleton
  fun provideSchedulers(): Schedulers = object : Schedulers {
    override fun subscribeOn(): Scheduler {
      return io.reactivex.schedulers.Schedulers.io()
    }

    override fun observeOn(): Scheduler {
      return AndroidSchedulers.mainThread()
    }
  }
}

