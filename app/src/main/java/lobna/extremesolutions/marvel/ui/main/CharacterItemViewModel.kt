package lobna.extremesolutions.marvel.ui.main

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import lobna.extremesolutions.marvel.R
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.ui.details.DetailsActivity
import lobna.extremesolutions.marvel.utils.IntentClass

class CharacterItemViewModel(
    val context: Context, val item: CharacterModel, query: String? = null
) {

    val nameObservable = ObservableField<SpannableString>()

    init {
        if (!query.isNullOrBlank()) {
            var index = item.name.indexOf(query, ignoreCase = true)
            val spannable = SpannableString(item.name)
            while (index != -1 && index < item.name.length) {
                spannable.setSpan(
                    BackgroundColorSpan(ContextCompat.getColor(context, R.color.red)),
                    index, index + query.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                index = item.name.indexOf(query, index + query.length + 1, ignoreCase = true)
            }
            nameObservable.set(spannable)
        }
    }

    fun onClick(view: View) {
        val bundle = Bundle().apply { putParcelable("character", item) }
        IntentClass.goToActivity(view.context, DetailsActivity::class.java, bundle)
    }
}