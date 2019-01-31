package vzijden.workout.view.home.schedule

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import vzijden.workout.domain.repository.WorkoutRepository
import vzijden.workout.domain.usecase.GetPlannedWorkouts

@Module
class ScheduleFragmentModule {
  @Provides
  fun getPlannedWorkoutsUseCase(workoutRepository: WorkoutRepository): GetPlannedWorkouts = GetPlannedWorkouts(workoutRepository,
      AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread())
}