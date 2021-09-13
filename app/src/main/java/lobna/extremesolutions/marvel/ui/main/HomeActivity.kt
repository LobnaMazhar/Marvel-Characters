package lobna.extremesolutions.marvel.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import lobna.extremesolutions.marvel.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        activityHomeBinding.hvm = homeViewModel

        lifecycleScope.launch {
            homeViewModel.getCharacters(this@HomeActivity).distinctUntilChanged()
                .collectLatest { homeViewModel.submitData(it) }
        }
    }
}