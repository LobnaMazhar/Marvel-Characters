package lobna.extremesolutions.marvel.datasource

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.data.CharactersListResponse
import lobna.extremesolutions.marvel.data.NetworkResponse
import lobna.extremesolutions.marvel.network.MyRetrofitClient.PAGE_SIZE
import lobna.extremesolutions.marvel.repository.CharactersRepository

class CharactersDataSource(private val context: Context, val name: String?) :
    PagingSource<Int, CharacterModel>() {

    private val TAG = CharactersDataSource::class.java.simpleName

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(PAGE_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        try {
            val offset = params.key ?: 0

            when (val response = makeApiCall(offset)) {
                is NetworkResponse.ErrorResponse ->
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                is NetworkResponse.ExceptionResponse ->
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                is NetworkResponse.DataResponse<*> -> {
                    (response.data as? CharactersListResponse<CharacterModel>)?.run {
                        if (code == 200) {
                            data.results.let {
                                val nextOffset =
                                    if (data.count == data.limit) offset + PAGE_SIZE else null
                                return LoadResult.Page(it, null, nextOffset)
                            }
                        }
                    }
                }
            }
            return LoadResult.Page(emptyList(), null, null)
        } catch (exception: Exception) {
            Log.e(TAG, "Failed to fetch data!")
            return LoadResult.Error(exception)
        }
    }

    private suspend fun makeApiCall(offset: Int): NetworkResponse {
        Log.d("CharacterList", "Getting $offset for $name")
        return CharactersRepository.getCharacters(offset, name)
    }
}