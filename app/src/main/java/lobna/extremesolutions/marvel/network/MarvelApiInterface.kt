package lobna.extremesolutions.marvel.network

import lobna.extremesolutions.marvel.BuildConfig
import lobna.extremesolutions.marvel.data.CharacterExtrasItemResourcesModel
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.data.CharactersListResponse
import lobna.extremesolutions.marvel.network.MyRetrofitClient.PAGE_SIZE
import lobna.extremesolutions.marvel.network.MyRetrofitClient.toHex
import lobna.extremesolutions.marvel.network.MyRetrofitClient.toMD5
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MarvelApiInterface {

    @GET("characters")
    suspend fun characters(
        @Query("offset") offset: Int = 0,
        @Query("limit") size: Int = PAGE_SIZE,
        @Query("ts") ts: String = Calendar.getInstance().timeInMillis.toString(),
        @Query("hash") hash: String = MyRetrofitClient.makeHashString(ts).toMD5().toHex(),
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY
    ): Response<CharactersListResponse<CharacterModel>>

    @GET("characters/{path}/{extras_type}")
    suspend fun extras(
        @Path("extras_type") extras_type: String,
        @Path("path") path: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") size: Int = PAGE_SIZE,
        @Query("ts") ts: String = Calendar.getInstance().timeInMillis.toString(),
        @Query("hash") hash: String = MyRetrofitClient.makeHashString(ts).toMD5().toHex(),
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY
    ): Response<CharactersListResponse<CharacterExtrasItemResourcesModel>>
}