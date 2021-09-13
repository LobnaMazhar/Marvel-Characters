package lobna.extremesolutions.marvel.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.data.NetworkResponse
import lobna.extremesolutions.marvel.datasource.CharactersDataSource
import lobna.extremesolutions.marvel.network.MarvelApiInterface
import lobna.extremesolutions.marvel.network.MyRetrofitClient

object CharactersRepository {

    private val marvelApi: MarvelApiInterface by lazy {
        MyRetrofitClient.createService(MarvelApiInterface::class.java)
    }

    suspend fun getCharacters(offset: Int = 0): NetworkResponse {
        return try {
            val response = marvelApi.characters(offset)

            if (response.isSuccessful) {
                NetworkResponse.DataResponse(response.body())
            } else {
                NetworkResponse.ErrorResponse(response.code(), response.message())
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { e.printStackTrace() }
            NetworkResponse.ExceptionResponse(e.message)
        }
    }

    fun getCharacters(context: Context): Flow<PagingData<CharacterModel>> {
        return Pager(PagingConfig(pageSize = CharactersDataSource.PAGE_SIZE)) {
            CharactersDataSource(context)
        }.flow
    }
}