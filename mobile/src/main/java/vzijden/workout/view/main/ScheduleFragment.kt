package vzijden.workout.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.schedule_fragment.*
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Workout
import vzijden.workout.databinding.ScheduleFragmentBinding
import vzijden.workout.databinding.ScheduleItemViewBinding
import vzijden.workout.view.BindableAdapter
import vzijden.workout.view.schedule.workout.EditWorkoutActivity


class ScheduleFragment : Fragment(), SchedulePresenter.View {
  companion object {
    private const val EDIT_WORKOUT_REQUEST = 1
    fun createInstance(): ScheduleFragment {
      val scheduleFragment = ScheduleFragment()
      val bundle = Bundle()
      scheduleFragment.arguments = bundle
      return scheduleFragment
    }
  }

  private lateinit var schedulePresenter: SchedulePresenter
  private lateinit var binding: ScheduleFragmentBinding

  private var scheduleAdapter: ScheduleAdapter? = null


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.schedule_fragment, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    schedulePresenter = SchedulePresenter(this, ScheduleDatabase.getInstance(requireContext()))
    binding.schedulePresenter = schedulePresenter

    scheduleAdapter = ScheduleAdapter()
    schedule_item_recycler_view.apply {
      adapter = scheduleAdapter
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
  }

  override fun editWorkout(workout: Workout) {
    val intent = Intent(context, EditWorkoutActivity::class.java)
    val bundle = EditWorkoutActivity.createIntent(workout.id)
    intent.putExtras(bundle)
    startActivityForResult(intent, EDIT_WORKOUT_REQUEST)
  }

  inner class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleItemViewholder>(), BindableAdapter<SchedulePresenter.ScheduleItemView> {
    private var scheduleAndWorkouts: List<SchedulePresenter.ScheduleItemView>? = null

    override fun setData(items: List<SchedulePresenter.ScheduleItemView>) {
      scheduleAndWorkouts = items
      notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
      positions.forEach(this::notifyItemChanged)
    }

    override fun getItemCount(): Int = scheduleAndWorkouts?.size ?: 0


    override fun onBindViewHolder(p0: ScheduleItemViewholder, p1: Int) {
      scheduleAndWorkouts?.get(p1)?.let { scheduleItemView ->
        p0.scheduleItemViewBinding.scheduleItemView = scheduleItemView
      }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ScheduleItemViewholder {
      val inflater = LayoutInflater.from(viewGroup.context)
      val binding: ScheduleItemViewBinding = DataBindingUtil.inflate(inflater, R.layout.schedule_item_view, viewGroup, false)
      return ScheduleItemViewholder(binding)
    }

    inner class ScheduleItemViewholder(val scheduleItemViewBinding: ScheduleItemViewBinding) : RecyclerView.ViewHolder(scheduleItemViewBinding.root)
  }
}