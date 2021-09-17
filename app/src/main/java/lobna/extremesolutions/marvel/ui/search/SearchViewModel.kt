package lobna.extremesolutions.marvel.ui.search

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.interfaces.CharacterItemInterface
import lobna.extremesolutions.marvel.repository.CharactersRepository
import lobna.extremesolutions.marvel.ui.main.LoaderStateAdapter
import lobna.extremesolutions.marvel.utils.SingleLiveEvent
import lobna.extremesolutions.marvel.utils.hideKeyboard

class SearchViewModel : ViewModel() {

    val searchEvent = SingleLiveEvent<String?>()
    val cancelEvent = SingleLiveEvent<Boolean>()

    val searchTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            viewModelScope.launch { submitData(PagingData.empty()) }
            searchEvent.postValue(s?.toString())
        }
    }

    val charactersAdapter = SearchAdapter().apply {
        withLoadStateFooter(LoaderStateAdapter())
    }

    fun getCharacters(context: Context, name: String? = null): Flow<PagingData<CharacterModel>> {
        return CharactersRepository.getCharacters(context, name)
    }

    suspend fun submitData(it: PagingData<CharacterModel>, name: String? = null) {
        charactersAdapter.query = name
        charactersAdapter.submitData(it)
    }

    fun cancel(view: View) {
        view.hideKeyboard()
        cancelEvent.postValue(true)
    }
}