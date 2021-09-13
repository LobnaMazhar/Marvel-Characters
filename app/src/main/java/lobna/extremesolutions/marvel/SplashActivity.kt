package lobna.extremesolutions.marvel

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import lobna.extremesolutions.marvel.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        Glide.with(this).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(60)))
            .into(activitySplashBinding.backgroundImage)

        Handler(Looper.getMainLooper()).postDelayed({ finish() }, 2000)
    }
}