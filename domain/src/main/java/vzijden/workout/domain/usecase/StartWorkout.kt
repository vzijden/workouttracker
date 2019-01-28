package vzijden.workout.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import vzijden.workout.domain.SingleUseCase
import vzijden.workout.domain.model.LoggedWorkout
import vzijden.workout.domain.repository.WorkoutRepository
import java.util.*

class StartWorkout(private val workoutRepository: WorkoutRepository, subscribeScheduler: Scheduler, postExecutionScheduler: Scheduler) :
    SingleUseCase<Long, Int>(subscribeScheduler, postExecutionScheduler) {

  override fun buildUseSingle(workoutId: Int?): Single<Long> {
    val workout = workoutRepository.getPlannedWorkout(workoutId!!)
    val loggedWorkout = LoggedWorkout(workout.blockingFirst(), Date())
    return workoutRepository.saveLoggedWorkout(loggedWorkout)
  }
}