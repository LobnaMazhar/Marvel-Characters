package lobna.extremesolutions.marvel.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.databinding.ItemLoadingBinding

class LoaderStateAdapter : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val itemLoadingBinding: ItemLoadingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_loading, parent, false
        )
        return LoaderViewHolder(itemLoadingBinding)
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoaderViewHolder(private val itemLoadingBinding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(itemLoadingBinding.root) {

        fun bind(loadState: LoadState) {
            itemLoadingBinding.marvelLoading.isVisible = loadState is LoadState.Loading
        }
    }
}
