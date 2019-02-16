package vzijden.workout.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class NoParamSingleUseCase<T>(private val subscribeScheduler: Scheduler,
                                                  private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(): Single<T>

  fun execute() : Single<T> {
    return build()
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
  }

  fun dispose() {
    disposables.clear()
  }
}