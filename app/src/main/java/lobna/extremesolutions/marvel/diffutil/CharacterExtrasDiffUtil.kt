package lobna.extremesolutions.marvel.diffutil

import androidx.recyclerview.widget.DiffUtil
import lobna.extremesolutions.marvel.data.CharacterExtrasItemResourcesModel

object CharacterExtrasDiffUtil : DiffUtil.ItemCallback<CharacterExtrasItemResourcesModel>() {

    override fun areItemsTheSame(
        oldItem: CharacterExtrasItemResourcesModel, newItem: CharacterExtrasItemResourcesModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterExtrasItemResourcesModel, newItem: CharacterExtrasItemResourcesModel
    ): Boolean {
        return oldItem == newItem
    }
}