package vzijden.workout.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.schedule_fragment.*
import kotlinx.android.synthetic.main.schedule_item_view.view.*
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.ScheduleAndWorkouts
import vzijden.workout.view.schedule.workout.EditWorkoutActivity



class ScheduleFragment : Fragment(), WorkoutsPresenter.View {
  companion object {
    private const val EDIT_WORKOUT_REQUEST = 1
    fun createInstance(): ScheduleFragment {
      val scheduleFragment = ScheduleFragment()
      val bundle = Bundle()
      scheduleFragment.arguments = bundle
      return scheduleFragment
    }
  }

  lateinit var workoutsPresenter: WorkoutsPresenter

  private var scheduleAndWorkouts: ScheduleAndWorkouts? = null
  private var scheduleAdapter: ScheduleAdapter? = null

  override fun setSchedule(schedule: ScheduleAndWorkouts) {
    scheduleAndWorkouts = schedule
    scheduleAdapter?.notifyDataSetChanged()
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.schedule_fragment, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    workoutsPresenter = WorkoutsPresenter(this, ScheduleDatabase.getInstance(requireContext()))

    scheduleAdapter = ScheduleAdapter()
    schedule_item_recycler_view.apply {
      adapter = scheduleAdapter
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    edit_schedule_button.setOnClickListener {
      val intent = Intent(context, EditWorkoutActivity::class.java)
      startActivityForResult(intent, EDIT_WORKOUT_REQUEST)
    }
  }

  inner class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleItemViewholder>() {
    override fun getItemCount(): Int {
      return scheduleAndWorkouts?.workouts?.size ?: 0
    }

    override fun onBindViewHolder(p0: ScheduleItemViewholder, p1: Int) {
      val workout = scheduleAndWorkouts?.workouts?.get(p1)
      p0.scheduleItemView.schedule_item_view_workout_name_text_field.text = workout?.name
      p0.scheduleItemView.schedule_item_view_workout_day_text_field.text = workout?.day.toString()
      p0.scheduleItemView.setOnClickListener { view ->
        val intent = Intent(context, EditWorkoutActivity::class.java)
        workout?.let {
          val bundle = EditWorkoutActivity.createIntent(workout.id)
          intent.putExtras(bundle)
        }
        startActivity(intent)
      }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ScheduleItemViewholder {
      val scheduleItemView = LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.schedule_item_view, viewGroup, false) as ViewGroup

      return ScheduleItemViewholder(scheduleItemView)
    }

    inner class ScheduleItemViewholder(val scheduleItemView: ViewGroup) : RecyclerView.ViewHolder(scheduleItemView)
  }
}