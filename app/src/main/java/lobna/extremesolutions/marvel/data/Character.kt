package lobna.extremesolutions.marvel.data

data class CharactersListResponse(
    val code: Int,
    val status: String,
    val data: CharactersDataResponse
)

data class CharactersDataResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailModel
)

data class ThumbnailModel(
    private val path: String,
    private val extension: String,
) {
    val imageUrl: String
        get() = "$path.$extension"
}