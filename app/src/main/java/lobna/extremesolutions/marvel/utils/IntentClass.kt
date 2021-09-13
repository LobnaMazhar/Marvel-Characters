package lobna.extremesolutions.marvel.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment

object IntentClass {
    fun goToActivity(
        activity: Activity,
        target: Class<*>,
        value: Bundle? = null,
        action: String? = null,
        enterAnim: Int = -1,
        exitAnim: Int = -1,
        clear: Boolean = false,
        top: Boolean = false,
        finish: Boolean = false
    ) {
        val intent = Intent(activity, target)
        if (clear)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (top)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("data", value)
        intent.action = action
        activity.startActivity(intent)
        if (enterAnim != -1 && exitAnim != -1)
            activity.overridePendingTransition(enterAnim, exitAnim)

        if (finish) activity.finish()
    }

    fun goToActivity(
        context: Context,
        target: Class<*>,
        value: Bundle? = null,
        action: String? = null,
        clear: Boolean = false,
        top: Boolean = false
    ) {
        val intent = Intent(context, target)
        if (clear)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (top)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("data", value)
        intent.action = action
        context.startActivity(intent)
    }
}