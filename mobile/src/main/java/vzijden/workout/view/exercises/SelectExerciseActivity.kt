package vzijden.workout.view.exercises

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_select_exercise.*
import vzijden.workout.R
import vzijden.workout.data.ScheduleDatabase
import vzijden.workout.data.model.Exercise
import vzijden.workout.databinding.ActivitySelectExerciseBinding
import vzijden.workout.databinding.ItemSelectExercisesActivityBinding
import vzijden.workout.view.AbstractAdapter

class SelectExerciseActivity : AppCompatActivity(), SelectExercisePresenter.SelectExerciseView {
  companion object {
    public const val RESULT_ID = "result"
  }

  lateinit var presenter: SelectExercisePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivitySelectExerciseBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_exercise)
    presenter = SelectExercisePresenter(this)
    binding.presenter = presenter
    setupExercisesRecycler()
  }

  override fun getDatabase(): ScheduleDatabase {
    return ScheduleDatabase.getInstance(this)
  }

  override fun selectExercise(exercise: Exercise) {
    val intent = Intent().apply {
      putExtra(RESULT_ID, exercise.id)
    }
    setResult(Activity.RESULT_OK, intent)
    finish()
  }

  override fun noneSelected() {
    setResult(Activity.RESULT_CANCELED)
    finish()
  }

  private fun setupExercisesRecycler() {
    recycler_view_select_exercise.apply {
      layoutManager = LinearLayoutManager(this@SelectExerciseActivity)
      adapter = ExercisesAdapter()
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
  }

  internal class ExercisesAdapter : AbstractAdapter<Exercise>() {
    override fun getHolderViewType(pos: Int): Int = 1


    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      (holder as ExerciseViewHolder).apply {
        binding.exercise = observableList[position]
      }
    }

    override fun createItemViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding: ItemSelectExercisesActivityBinding = DataBindingUtil.inflate(inflater, R.layout.item_select_exercises_activity, parent, false)
      return ExerciseViewHolder(binding)
    }

    internal class ExerciseViewHolder(val binding: ItemSelectExercisesActivityBinding) : RecyclerView.ViewHolder(binding.root)


  }
}