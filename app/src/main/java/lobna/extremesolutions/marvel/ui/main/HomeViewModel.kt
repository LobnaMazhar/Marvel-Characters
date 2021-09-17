package lobna.extremesolutions.marvel.ui.main

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.repository.CharactersRepository
import lobna.extremesolutions.marvel.utils.SingleLiveEvent

class HomeViewModel : ViewModel() {

    val searchEvent = SingleLiveEvent<Boolean>()

    val charactersAdapter = CharactersAdapter().apply {
        withLoadStateFooter(LoaderStateAdapter())
    }

    fun getCharacters(context: Context): Flow<PagingData<CharacterModel>> {
        return CharactersRepository.getCharacters(context)
    }

    suspend fun submitData(it: PagingData<CharacterModel>) {
        charactersAdapter.submitData(it)
    }

    fun search(view: View) {
        searchEvent.postValue(true)
    }
}