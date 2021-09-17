package lobna.extremesolutions.marvel.interfaces

import android.widget.ImageView
import android.widget.TextView
import lobna.extremesolutions.marvel.data.CharacterModel

interface CharacterItemInterface {
    fun onItemClick(item: CharacterModel, imageView: ImageView, textView: TextView)
}