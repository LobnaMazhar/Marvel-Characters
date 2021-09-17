package lobna.extremesolutions.marvel.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.databinding.ItemSearchResultBinding
import lobna.extremesolutions.marvel.diffutil.CharactersDiffUtil
import lobna.extremesolutions.marvel.ui.main.CharacterItemViewModel

class SearchAdapter :
    PagingDataAdapter<CharacterModel, SearchAdapter.SearchViewHolder>(CharactersDiffUtil) {

     var query: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemSearchResultBinding: ItemSearchResultBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_search_result, parent, false
        )
        return SearchViewHolder(itemSearchResultBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    inner class SearchViewHolder(var itemSearchResultBinding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(itemSearchResultBinding.root) {

        fun bind(item: CharacterModel) {
            itemSearchResultBinding.civm =
                CharacterItemViewModel(itemSearchResultBinding.root.context, item, query)
        }
    }
}