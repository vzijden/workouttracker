package vzijden.workout.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params>(private val subscribeScheduler: Scheduler,
                                               private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  abstract fun build(params: Params?): Observable<T>

  fun execute(observer: DisposableObserver<T>, params: Params? = null) {
    val observable: Observable<T> = this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
    disposables.add(observable.subscribeWith(observer))
  }

  fun dispose() {
    disposables.clear()
  }
}