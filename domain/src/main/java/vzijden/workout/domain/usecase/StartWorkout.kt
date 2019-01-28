package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import java.util.*

class StartWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<Long, Int>(subscribeScheduler, postExecutionScheduler) {

  override fun build(workoutId: Int?): Single<Long> {
    val loggedWorkout = LoggedWorkout(Date(), workoutId!!)
    return workoutRepository.saveLoggedWorkout(loggedWorkout)
  }
}