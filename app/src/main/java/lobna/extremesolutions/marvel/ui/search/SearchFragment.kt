package lobna.extremesolutions.marvel.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel>()

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentSearchBinding: FragmentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        fragmentSearchBinding.svm = searchViewModel

        searchViewModel.searchEvent.observe(this, { doSearch(it) })
        searchViewModel.cancelEvent.observe(this, { requireActivity().onBackPressed() })

        return fragmentSearchBinding.root
    }

    /**
     * Search for marvel characters with [name] implementing Debounce method with a delay of 0.5 second
     *
     * [name] is the name of the character to search for
     *
     * Cancel any running job the fire a new job which waits 0.5 second;
     * then go search for the characters if it's still running.
     * */
    private fun doSearch(name: String?) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(500)
            searchViewModel.getCharacters(requireContext(), name).distinctUntilChanged()
                .collectLatest { searchViewModel.submitData(it, name) }
        }
    }
}