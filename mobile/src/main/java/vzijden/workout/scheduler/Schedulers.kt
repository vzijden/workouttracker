package vzijden.workout.scheduler

import io.reactivex.Scheduler

interface Schedulers {
  fun observeOn(): Scheduler
  fun subscribeOn(): Scheduler
}