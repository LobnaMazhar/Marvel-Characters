package lobna.extremesolutions.marvel.data

import android.os.Parcel
import android.os.Parcelable

data class CharactersListResponse<T>(
    val code: Int,
    val status: String,
    val data: CharactersDataResponse<T>
)

data class CharactersDataResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailModel?,
    val comics: CharacterExtrasModel?,
    val series: CharacterExtrasModel?,
    val stories: CharacterExtrasModel?,
    val events: CharacterExtrasModel?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(ThumbnailModel::class.java.classLoader),
        null,
        null,
        null,
        null
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeParcelable(thumbnail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterModel> {
        override fun createFromParcel(parcel: Parcel): CharacterModel {
            return CharacterModel(parcel)
        }

        override fun newArray(size: Int): Array<CharacterModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class ThumbnailModel(
    private val path: String,
    private val extension: String,
) : Parcelable {
    val imageUrl: String
        get() = "$path.$extension"

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeString(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThumbnailModel> {
        override fun createFromParcel(parcel: Parcel): ThumbnailModel {
            return ThumbnailModel(parcel)
        }

        override fun newArray(size: Int): Array<ThumbnailModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class CharacterExtrasModel(
    var items: List<CharacterExtrasItemModel>
)

data class CharacterExtrasItemModel(
    val resourceURI: String,
    val name: String
)

data class CharacterExtrasItemResourcesModel(
    val id: Int,
    val title: String,
    val thumbnail: ThumbnailModel
)