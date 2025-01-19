package com.hopcape.mobile.auth.api.launcher

import android.app.Activity
import android.content.Intent
import com.hopcape.mobile.auth.presentation.ui.AndroidAuthenticationHostActivity

/**
 * A class that launches the authentication flow on Android.
 * This class is responsible for initiating the authentication process by starting an [AndroidAuthenticationHostActivity].
 *
 * ##### Example usage:
 * ```kotlin
 * val activity: Activity = /* Your activity instance */
 * val authFlowLauncher = AndroidAuthenticationFlowLauncher(activity)
 * authFlowLauncher.launchAuthenticationFlow()
 * ```
 *
 * When `launchAuthenticationFlow()` is called, it starts the `AndroidAuthenticationHostActivity` and finishes the current activity.
 * This behavior is commonly used when transitioning to a dedicated authentication screen.
 *
 * @param activity The activity used to launch the authentication flow.
 *
 * @author Murtaza Khursheed
 */
class AndroidAuthenticationFlowLauncher(
    private val activity: Activity
): AuthenticationFlowLauncher {

    /**
     * Initiates and launches the authentication flow.
     *
     * This method is responsible for starting the authentication activity ([AndroidAuthenticationHostActivity]).
     * It also finishes the current activity to complete the authentication transition.
     *
     * Example:
     * ```kotlin
     * val authFlowLauncher = AndroidAuthenticationFlowLauncher(activity)
     * authFlowLauncher.launchAuthenticationFlow()
     * ```
     */
    override fun launchAuthenticationFlow() {
        Intent(activity, AndroidAuthenticationHostActivity::class.java)
            .also {
                activity.startActivity(it)
                activity.finish()
            }
    }
}
