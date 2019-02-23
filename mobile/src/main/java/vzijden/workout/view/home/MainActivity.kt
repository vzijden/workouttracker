package vzijden.workout.view.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.App
import vzijden.workout.R
import vzijden.workout.data.WorkoutDatabase
import vzijden.workout.data.model.PlannedExercisePojo
import vzijden.workout.data.model.PlannedSetPojo
import vzijden.workout.data.model.PlannedWorkoutPojo
import vzijden.workout.data.model.SchedulePojo
import vzijden.workout.data.util.initialize
import vzijden.workout.view.home.schedule.ScheduleFragment
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var workoutDatabase: WorkoutDatabase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val schedule = SchedulePojo("firstSchedule")
    doAsync {

      if (workoutDatabase.exerciseDao().getAll().blockingFirst().isEmpty()) {
        Log.d(App.TAG, "initializing database")
        initialize(workoutDatabase, this@MainActivity.resources.openRawResource(R.raw.exercises))
        val scheduleId = workoutDatabase.scheduleDao().insert(schedule)
        val workoutId = workoutDatabase.workoutDao().insert(PlannedWorkoutPojo(scheduleId.toInt(), "Chest", 1)).blockingGet()
        val exercises = workoutDatabase.exerciseDao().getAll().blockingFirst()
        val registrationId = workoutDatabase.registrationDao().insert(PlannedExercisePojo(workoutId, exercises[0])).blockingGet()
        workoutDatabase.setsDao().insert(PlannedSetPojo(8, 0, registrationId)).blockingGet()
        workoutDatabase.setsDao().insert(PlannedSetPojo(8, 1, registrationId)).blockingGet()
      }

      uiThread {
        setupViewPager()
        setupNavigationView()
      }
    }
  }

  private lateinit var viewPager: ViewPager

  private fun setupViewPager() {
    viewPager = viewpager
    val adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
      override fun getCount(): Int {
        return 2
      }

      override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        when (p0) {
          0 -> return ScheduleFragment.createInstance()
          1 -> return WorkoutsFragment()
          else -> {
            throw RuntimeException()
          }
        }
      }

    }

    viewPager.adapter = adapter
  }

  private fun setupNavigationView() {
    val navigationView = navigation_view
    navigationView.setOnNavigationItemSelectedListener { p0 ->
      when (p0.itemId) {
        R.id.navigation_schedule -> viewPager.currentItem = 0
        R.id.navigation_workouts -> viewPager.currentItem = 1
      }
      true
    }

    viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(p0: Int) {

      }

      override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

      }

      override fun onPageSelected(p0: Int) {
        navigationView.menu.getItem(p0).isChecked = true
      }

    })
  }
}
