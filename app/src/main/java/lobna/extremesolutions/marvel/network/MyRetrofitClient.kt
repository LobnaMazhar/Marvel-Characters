package lobna.extremesolutions.marvel.network

import com.google.gson.GsonBuilder
import lobna.extremesolutions.marvel.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object MyRetrofitClient {

    const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    internal var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    fun makeHashString(ts: String): String =
        ts + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY
    fun String.toMD5(): ByteArray = MessageDigest.getInstance("MD5").digest(toByteArray(UTF_8))
    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
}