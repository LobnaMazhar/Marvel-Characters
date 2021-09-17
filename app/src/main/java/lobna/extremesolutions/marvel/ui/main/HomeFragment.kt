package lobna.extremesolutions.marvel.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.databinding.FragmentHomeBinding
import lobna.extremesolutions.marvel.interfaces.CharacterItemInterface
import lobna.extremesolutions.marvel.ui.details.DetailsActivity
import lobna.extremesolutions.marvel.utils.IntentClass

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentHomeBinding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        fragmentHomeBinding.hvm = homeViewModel

        homeViewModel.characterItemInterface = object : CharacterItemInterface {
            override fun onItemClick(
                item: CharacterModel, imageView: ImageView, textView: TextView
            ) {
                val bundle = Bundle().apply {
                    putParcelable("character", item)
                    putBoolean("hasComics", !(item.comics?.items?.isNullOrEmpty() ?: true))
                    putBoolean("hasEvents", !(item.events?.items?.isNullOrEmpty() ?: true))
                    putBoolean("hasSeries", !(item.series?.items?.isNullOrEmpty() ?: true))
                    putBoolean("hasStories", !(item.stories?.items?.isNullOrEmpty() ?: true))
                }

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    Pair(imageView, ViewCompat.getTransitionName(imageView) ?: ""),
                    Pair(textView, ViewCompat.getTransitionName(textView) ?: "")
                )
                IntentClass.goToActivity(
                    requireActivity(), DetailsActivity::class.java, bundle, options = options
                )
            }

        }

        lifecycleScope.launch {
            homeViewModel.getCharacters(requireContext()).distinctUntilChanged()
                .collectLatest { homeViewModel.submitData(it) }
        }

        homeViewModel.searchEvent.observe(this,
            { findNavController().navigate(HomeFragmentDirections.homeToSearch()) })

        return fragmentHomeBinding.root
    }

}