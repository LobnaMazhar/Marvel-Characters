package lobna.extremesolutions.marvel.ui.details

import android.view.View
import lobna.extremesolutions.marvel.data.CharacterExtrasItemResourcesModel
import lobna.extremesolutions.marvel.interfaces.CharacterExtraItemInterface

class CharacterExtrasItemViewModel(
    val item: CharacterExtrasItemResourcesModel,
    private val characterExtraItemInterface: CharacterExtraItemInterface
) {

    fun clickItem(view: View) {
        characterExtraItemInterface.onItemClick(item)
    }
}