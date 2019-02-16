package vzijden.workout.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params>(private val subscribeScheduler: Scheduler,
                                             private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(params: Params?): Completable

  fun execute(params: Params? = null): Completable {
    return this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
  }

  fun dispose() {
    disposables.clear()
  }
}
