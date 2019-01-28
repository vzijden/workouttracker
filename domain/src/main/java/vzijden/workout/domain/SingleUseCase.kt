package vzijden.workout.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params>(private val subscribeScheduler: Scheduler,
                                           private val postExecutionScheduler: Scheduler) {

  private val disposables: CompositeDisposable = CompositeDisposable()

  abstract fun build(params: Params?): Single<T>

  fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
    val observable: Single<T> = this.build(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(postExecutionScheduler)
    disposables.add(observable.subscribeWith(observer))
  }

  fun dispose() {
    disposables.clear()
  }
}