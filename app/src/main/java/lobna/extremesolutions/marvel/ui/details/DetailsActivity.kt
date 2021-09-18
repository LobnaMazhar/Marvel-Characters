package lobna.extremesolutions.marvel.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activityDetailsBinding.root)
        supportPostponeEnterTransition()

        activityDetailsBinding.dvm = detailsViewModel

        val bundle = intent.getBundleExtra("data")
        bundle?.run {
            val character = getParcelable<CharacterModel>("character")
            activityDetailsBinding.characterImage.transitionName = character?.thumbnail?.imageUrl
            activityDetailsBinding.characterName.transitionName = character?.name
            detailsViewModel.init(character)
            supportStartPostponedEnterTransition()
            getData(this)
        }

        detailsViewModel.onBackEvent.observe(this, { onBackPressed() })
    }

    /**
     * Get extra data (Comics, Events, Series, Stories) for the character
     * Check first if the data exists before doing a useless request
     * Use the [bundle] to check whether extras exist or not through a boolean for each extra type
     * */
    private fun getData(bundle: Bundle) {
        bundle.run {
            if (containsKey("hasComics") && getBoolean("hasComics"))
                lifecycleScope.launch {
                    detailsViewModel.getComics(this@DetailsActivity).distinctUntilChanged()
                        .collectLatest { detailsViewModel.submitComics(it) }
                }

            if (containsKey("hasEvents") && getBoolean("hasEvents"))
                lifecycleScope.launch {
                    detailsViewModel.getEvents(this@DetailsActivity).distinctUntilChanged()
                        .collectLatest { detailsViewModel.submitEvents(it) }
                }

            if (containsKey("hasSeries") && getBoolean("hasSeries"))
                lifecycleScope.launch {
                    detailsViewModel.getSeries(this@DetailsActivity).distinctUntilChanged()
                        .collectLatest { detailsViewModel.submitSeries(it) }
                }

            if (containsKey("hasStories") && getBoolean("hasStories"))
                lifecycleScope.launch {
                    detailsViewModel.getStories(this@DetailsActivity).distinctUntilChanged()
                        .collectLatest { detailsViewModel.submitStories(it) }
                }
        }
    }

    override fun onBackPressed() {
        if (detailsViewModel.showExtraOverlayViewObservable.get())
            detailsViewModel.showExtraOverlayViewObservable.set(false)
        else super.onBackPressed()
    }
}