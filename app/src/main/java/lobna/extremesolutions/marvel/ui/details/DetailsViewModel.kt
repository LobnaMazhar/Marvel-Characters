package lobna.extremesolutions.marvel.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import lobna.extremesolutions.marvel.data.CharacterExtrasItemResourcesModel
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.repository.CharactersRepository
import lobna.extremesolutions.marvel.utils.SingleLiveEvent

class DetailsViewModel : ViewModel() {

    private lateinit var characterId: String

    val characterObservable = ObservableField<CharacterModel>()
    val descriptionAvailableObservable = ObservableBoolean(false)
    val comicsAvailableObservable = ObservableBoolean(false)
    val eventsAvailableObservable = ObservableBoolean(false)
    val seriesAvailableObservable = ObservableBoolean(false)
    val storiesAvailableObservable = ObservableBoolean(false)

    val comicsAdapter = CharacterExtrasAdapter()
    val eventsAdapter = CharacterExtrasAdapter()
    val seriesAdapter = CharacterExtrasAdapter()
    val storiesAdapter = CharacterExtrasAdapter()

    val onBackEvent = SingleLiveEvent<Boolean>()

    fun init(bundle: Bundle?) {
        bundle?.run {
            val character = getParcelable<CharacterModel>("character")
            character?.run {
                characterId = id.toString()
                characterObservable.set(character)
                descriptionAvailableObservable.set(description.isNotBlank())
            }
        }
    }

    fun onBackClick(view: View) {
        onBackEvent.postValue(true)
    }

    fun getComics(context: Context): Flow<PagingData<CharacterExtrasItemResourcesModel>> {
        return CharactersRepository.getExtras(context, "comics", characterId)
    }

    fun getEvents(context: Context): Flow<PagingData<CharacterExtrasItemResourcesModel>> {
        return CharactersRepository.getExtras(context, "events", characterId)
    }

    fun getSeries(context: Context): Flow<PagingData<CharacterExtrasItemResourcesModel>> {
        return CharactersRepository.getExtras(context, "series", characterId)
    }

    fun getStories(context: Context): Flow<PagingData<CharacterExtrasItemResourcesModel>> {
        return CharactersRepository.getExtras(context, "stories", characterId)
    }

    suspend fun submitComics(it: PagingData<CharacterExtrasItemResourcesModel>) {
        comicsAvailableObservable.set(true)
        comicsAdapter.submitData(it)
    }

    suspend fun submitEvents(it: PagingData<CharacterExtrasItemResourcesModel>) {
        eventsAvailableObservable.set(true)
        eventsAdapter.submitData(it)
    }

    suspend fun submitSeries(it: PagingData<CharacterExtrasItemResourcesModel>) {
        seriesAvailableObservable.set(true)
        seriesAdapter.submitData(it)
    }

    suspend fun submitStories(it: PagingData<CharacterExtrasItemResourcesModel>) {
        storiesAvailableObservable.set(true)
        storiesAdapter.submitData(it)
    }
}