package lobna.extremesolutions.marvel.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterExtrasItemResourcesModel
import lobna.extremesolutions.marvel.databinding.ItemCharacterExtrasBinding
import lobna.extremesolutions.marvel.diffutil.CharacterExtrasDiffUtil
import lobna.extremesolutions.marvel.interfaces.CharacterExtraItemInterface

class CharacterExtrasAdapter(val characterExtraItemInterface: CharacterExtraItemInterface) :
    PagingDataAdapter<CharacterExtrasItemResourcesModel, CharacterExtrasAdapter.CharacterExtrasViewHolder>(
        CharacterExtrasDiffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterExtrasViewHolder {
        val itemCharacterExtrasBinding: ItemCharacterExtrasBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_character_extras, parent, false
        )
        return CharacterExtrasViewHolder(itemCharacterExtrasBinding)
    }

    override fun onBindViewHolder(holder: CharacterExtrasViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    inner class CharacterExtrasViewHolder(var itemCharacterExtrasBinding: ItemCharacterExtrasBinding) :
        RecyclerView.ViewHolder(itemCharacterExtrasBinding.root) {

        fun bind(item: CharacterExtrasItemResourcesModel) {
            itemCharacterExtrasBinding.civm =
                CharacterExtrasItemViewModel(item, characterExtraItemInterface)
        }
    }
}