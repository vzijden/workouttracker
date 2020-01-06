package vzijden.workout.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params>(protected val subscribeScheduler: Scheduler,
                                             protected val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(params: Params): Completable

  fun execute(params: Params): Completable {
    return this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
  }

  fun dispose() {
    disposables.clear()
  }
}
