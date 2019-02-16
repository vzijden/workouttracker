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
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_exercise.*
import vzijden.workout.R
import vzijden.workout.databinding.ActivitySelectExerciseBinding
import vzijden.workout.databinding.ItemSelectExercisesActivityBinding
import vzijden.workout.domain.model.Exercise
import vzijden.workout.domain.usecase.GetAllExercises
import vzijden.workout.view.AbstractAdapter
import javax.inject.Inject

class SelectExerciseActivity : DaggerAppCompatActivity(), SelectExerciseViewModel.SelectExerciseView {
  companion object {
    const val RESULT_ID = "result"
  }

  @Inject
  lateinit var getAllExercises: GetAllExercises
  lateinit var viewModel: SelectExerciseViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivitySelectExerciseBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_exercise)
    viewModel = SelectExerciseViewModel(this, getAllExercises)
    binding.presenter = viewModel
    setupExercisesRecycler()
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