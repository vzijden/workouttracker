package vzijden.workout.view.home

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
import vzijden.workout.databinding.OnItemClickedListener
import vzijden.workout.view.AbstractAdapter
import vzijden.workout.view.schedule.workout.EditWorkoutActivity
import vzijden.workout.view.workout.CurrentWorkoutActivity


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

  override fun startWorkout(workout: Workout) {
    val intent = Intent(context, CurrentWorkoutActivity::class.java)
    val bundle = CurrentWorkoutActivity.createBundle(workout.id)
    intent.putExtras(bundle)
    startActivity(intent)
  }

  inner class ScheduleAdapter : AbstractAdapter<SchedulePresenter.ScheduleItemView>() {
    override fun getHolderViewType(): Int = 1

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val binding: ScheduleItemViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.schedule_item_view, parent, false)
      return ScheduleItemViewholder(binding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      observableList[position]?.let { scheduleItemView ->
        (holder as ScheduleItemViewholder).scheduleItemViewBinding.scheduleItemView = scheduleItemView
      }
    }

    override fun addOnItemClickedListener(listener: OnItemClickedListener<SchedulePresenter.ScheduleItemView>) {
    }

    inner class ScheduleItemViewholder(val scheduleItemViewBinding: ScheduleItemViewBinding) : RecyclerView.ViewHolder(scheduleItemViewBinding.root)
  }
}