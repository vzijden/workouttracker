package vzijden.workout.view.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.*
import vzijden.workout.data.model.Set
import java.util.*


class MainActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val schedule = Schedule("firstSchedule")
    doAsync {
      val scheduleDatabase = ScheduleDatabase.getInstance(applicationContext)
      val scheduleId = scheduleDatabase.scheduleDao().insert(schedule)
      val workout1Id = scheduleDatabase.workoutDao().insert(Workout(Date(), scheduleId.toInt(), "Chest and triceps", 1))
      val workout2Id = scheduleDatabase.workoutDao().insert(Workout(Date(), scheduleId.toInt(), "Shoulders and abs", 2))
      val workout3Id = scheduleDatabase.workoutDao().insert(Workout(Date(), scheduleId.toInt(), "Back and biceps", 3))

      val exercise1Id = scheduleDatabase.exerciseDao().insert(Exercise("Bench press", workout1Id.toInt(), MuscleGroup.BICEPS))
      scheduleDatabase.setsDao().insert(Set(8, exercise1Id.toInt()))
      scheduleDatabase.setsDao().insert(Set(8, exercise1Id.toInt()))
      scheduleDatabase.setsDao().insert(Set(8, exercise1Id.toInt()))

      scheduleDatabase.exerciseDao().insert(Exercise("Leg press", workout1Id.toInt(), MuscleGroup.LEGS))
      scheduleDatabase.exerciseDao().insert(Exercise("Overhead press", workout1Id.toInt(), MuscleGroup.SHOULDERS))
      scheduleDatabase.exerciseDao().insert(Exercise("Barbell curl", workout1Id.toInt(), MuscleGroup.BICEPS))
      scheduleDatabase.exerciseDao().insert(Exercise("Squats", workout1Id.toInt(), MuscleGroup.LEGS))

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

      override fun getItem(p0: Int): Fragment {
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
