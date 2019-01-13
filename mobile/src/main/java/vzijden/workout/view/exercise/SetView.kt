package vzijden.workout.view.exercise

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.edit_exercise_set_item_view.view.*


class SetView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
  private var amountTextView: TextView? = null
  private var slider: SeekBar? = null
  private var amount: Int = 0

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    amountTextView = set_item_view_amount
    slider = set_item_view_seek_bar
    slider?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        amount = progress
        updateTextView()
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {
      }

      override fun onStopTrackingTouch(seekBar: SeekBar?) {
      }
    })
    updateTextView()
    updateProgress()
  }

  public fun setAmount(amount: Int) {
    this.amount = amount
    updateTextView()
    updateProgress()
  }

  private fun updateTextView() {
    amountTextView?.text = amount.toString()
  }

  private fun updateProgress() {
    slider?.progress = amount
  }
}