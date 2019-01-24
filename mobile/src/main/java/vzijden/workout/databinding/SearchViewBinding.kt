package vzijden.workout.databinding

import android.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("setSearchQueryTextListener")
fun setSearchQueryTextListener(searchView: SearchView, onQueryTextListener: SearchView.OnQueryTextListener) {
  searchView.setOnQueryTextListener(onQueryTextListener)
}