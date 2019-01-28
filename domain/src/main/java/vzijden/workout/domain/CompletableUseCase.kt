package vzijden.workout.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase< in Params>(private val subscribeScheduler: Scheduler,
                                                private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  abstract fun buildCompletable(params: Params?): Completable

  fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
    val completable: Completable = this.buildCompletable(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
    disposables.add(completable.subscribeWith(observer))
  }

  fun dispose() {
    disposables.clear()
  }
}
