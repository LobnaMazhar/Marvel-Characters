package lobna.extremesolutions.marvel.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.ui.main.CharactersAdapter

fun View.hideKeyboard() {
    try {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun RecyclerView.setupParallaxScrollListener() {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = layoutManager as? LinearLayoutManager ?: return

            val scrollOffset = recyclerView.computeVerticalScrollOffset()
            val offsetFactor = (scrollOffset % measuredHeight) / measuredHeight.toFloat()

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            for (i in firstVisibleItemPosition until lastVisibleItemPosition) {
                findViewHolderForAdapterPosition(i)?.let {
                    (it as? CharactersAdapter.CharactersViewHolder)?.offset = -offsetFactor
                }
            }
        }
    })
}