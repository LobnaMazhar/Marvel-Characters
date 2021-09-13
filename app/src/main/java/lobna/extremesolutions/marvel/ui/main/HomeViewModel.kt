package lobna.extremesolutions.marvel.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.repository.CharactersRepository

class HomeViewModel : ViewModel() {

    val charactersAdapter = CharactersAdapter().apply {
        withLoadStateFooter(LoaderStateAdapter())
    }

    fun getCharacters(context: Context): Flow<PagingData<CharacterModel>> {
        return CharactersRepository.getCharacters(context)
    }

    suspend fun submitData(it: PagingData<CharacterModel>) {
        charactersAdapter.submitData(it)
    }
}