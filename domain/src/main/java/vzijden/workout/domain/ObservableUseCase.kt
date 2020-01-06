package vzijden.workout.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params>(protected val subscribeScheduler: Scheduler,
                                               protected val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(params: Params): Observable<T>

  fun execute(params: Params): Observable<T> {
    return this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
  }

  fun dispose() {
    disposables.clear()
  }
}