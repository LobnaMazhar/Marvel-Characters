package lobna.extremesolutions.marvel.diffutil

import androidx.recyclerview.widget.DiffUtil
import lobna.extremesolutions.marvel.data.CharacterModel

object CharactersDiffUtil : DiffUtil.ItemCallback<CharacterModel>() {

    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}