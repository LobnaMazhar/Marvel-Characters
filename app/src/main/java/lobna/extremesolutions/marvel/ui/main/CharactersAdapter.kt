package lobna.extremesolutions.marvel.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.databinding.ItemCharacterBinding
import lobna.extremesolutions.marvel.diffutil.CharactersDiffUtil

class CharactersAdapter :
    PagingDataAdapter<CharacterModel, CharactersAdapter.CharactersViewHolder>(CharactersDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemCharacterBinding: ItemCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_character, parent, false
        )
        return CharactersViewHolder(itemCharacterBinding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    inner class CharactersViewHolder(var itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {

        fun bind(item: CharacterModel) {
            itemCharacterBinding.civm = CharacterItemViewModel(item)
        }
    }
}