package lobna.extremesolutions.marvel.network

import lobna.extremesolutions.marvel.BuildConfig
import lobna.extremesolutions.marvel.data.CharactersListResponse
import lobna.extremesolutions.marvel.datasource.CharactersDataSource
import lobna.extremesolutions.marvel.network.MyRetrofitClient.toHex
import lobna.extremesolutions.marvel.network.MyRetrofitClient.toMD5
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MarvelApiInterface {

    @GET("characters")
    suspend fun characters(
        @Query("offset") offset: Int = 0,
        @Query("limit") size: Int = CharactersDataSource.PAGE_SIZE,
        @Query("ts") ts: String = Calendar.getInstance().timeInMillis.toString(),
        @Query("hash") hash: String = MyRetrofitClient.makeHashString(ts).toMD5().toHex(),
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY
    ): Response<CharactersListResponse>
}