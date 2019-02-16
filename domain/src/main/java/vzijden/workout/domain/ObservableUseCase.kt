package vzijden.workout.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params>(private val subscribeScheduler: Scheduler,
                                               private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected abstract fun build(params: Params?): Observable<T>

  fun execute(params: Params? = null): Observable<T> {
    return this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
  }

  fun dispose() {
    disposables.clear()
  }
}