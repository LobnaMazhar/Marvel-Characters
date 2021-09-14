package lobna.extremesolutions.marvel.ui.main

import android.os.Bundle
import android.view.View
import lobna.extremesolutions.marvel.data.CharacterModel
import lobna.extremesolutions.marvel.ui.details.DetailsActivity
import lobna.extremesolutions.marvel.utils.IntentClass

class CharacterItemViewModel(val item: CharacterModel) {

    fun onClick(view: View) {
        val bundle = Bundle().apply { putParcelable("character", item) }
        IntentClass.goToActivity(view.context, DetailsActivity::class.java, bundle)
    }
}