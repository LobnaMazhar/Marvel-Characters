package lobna.extremesolutions.marvel.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.databinding.ItemCharacterBinding
import lobna.extremesolutions.marvel.diffutil.CharactersDiffUtil
import lobna.extremesolutions.marvel.interfaces.CharacterItemInterface
import kotlin.math.abs

class CharactersAdapter(val characterItemInterface: CharacterItemInterface) :
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

        private val interpolator = FastOutLinearInInterpolator()
        var offset: Float = 0f
            set(v) {
                field = v.coerceIn(-0.5f, 0.5f)

                val direction = if (field < 0) -0.5f else 0.5f
                val interpolatedValue = interpolator.getInterpolation(abs(field))
                val translationY = direction * interpolatedValue * itemView.measuredHeight

                itemCharacterBinding.characterImage.translationY = translationY
            }

        fun bind(item: CharacterModel) {
            itemCharacterBinding.civm =
                CharacterItemViewModel(itemCharacterBinding.root.context, item)

            ViewCompat.setTransitionName(
                itemCharacterBinding.characterImage, item.thumbnail?.imageUrl
            )
            ViewCompat.setTransitionName(itemCharacterBinding.characterName, item.name)

            itemCharacterBinding.root.setOnClickListener {
                characterItemInterface.onItemClick(
                    item, itemCharacterBinding.characterImage, itemCharacterBinding.characterName
                )
            }
        }
    }
}