package vzijden.workout.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class SingleUseCase<T, in Params>(private val subscribeScheduler: Scheduler,
                                           private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(params: Params): Single<T>

  fun execute(params: Params) : Single<T> {
    return build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)

  }

  fun dispose() {
    disposables.clear()
  }
}