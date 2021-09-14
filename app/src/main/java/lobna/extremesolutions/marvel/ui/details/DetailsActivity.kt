package lobna.extremesolutions.marvel.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activityDetailsBinding.root)

        activityDetailsBinding.dvm = detailsViewModel

        detailsViewModel.init(intent.getBundleExtra("data"))

        detailsViewModel.onBackEvent.observe(this, { onBackPressed() })

        lifecycleScope.launch {
            detailsViewModel.getComics(this@DetailsActivity).distinctUntilChanged()
                .collectLatest { detailsViewModel.submitComics(it) }

            detailsViewModel.getEvents(this@DetailsActivity).distinctUntilChanged()
                .collectLatest { detailsViewModel.submitEvents(it) }

            detailsViewModel.getSeries(this@DetailsActivity).distinctUntilChanged()
                .collectLatest { detailsViewModel.submitSeries(it) }

            detailsViewModel.getStories(this@DetailsActivity).distinctUntilChanged()
                .collectLatest { detailsViewModel.submitStories(it) }
        }
    }
}