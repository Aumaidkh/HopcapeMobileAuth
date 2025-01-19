package com.hopcape.mobile.auth.api.launcher

import com.hopcape.mobile.auth.presentation.ui.authenticationHostViewController
import platform.UIKit.UIWindow

/**
 * A class that implements [AuthenticationFlowLauncher] to handle launching the authentication flow on iOS.
 * This class uses a [UIWindow] to set up the root view controller, which hosts the authentication UI.
 * The authentication UI is defined in the `authenticationHostViewController`, which is responsible for displaying
 * the authentication screen in the iOS environment.
 *
 * ##### Example usage:
 * ```kotlin
 * val iosAuthenticationFlowLauncher = IOSAuthenticationFlowLauncher(window)
 * iosAuthenticationFlowLauncher.launchAuthenticationFlow()
 * ```
 * In this example, the `IOSAuthenticationFlowLauncher` is initialized with a `UIWindow` object, and the
 * `launchAuthenticationFlow()` method is called to set the authentication UI as the root view controller of the window.
 *
 * @param window The [UIWindow] instance that will display the authentication flow.
 * @author Murtaza Khursheed
 */
class IOSAuthenticationFlowLauncher(
    private val window: UIWindow
): AuthenticationFlowLauncher {

    /**
     * Initiates and launches the authentication flow by setting the authentication host view controller
     * as the root view controller of the provided [UIWindow].
     *
     * This method is responsible for navigating to the authentication screen by updating the root view
     * controller of the window with the authentication UI (`authenticationHostViewController`).
     *
     * Example:
     * ```kotlin
     * iosAuthenticationFlowLauncher.launchAuthenticationFlow()
     * ```
     */
    override fun launchAuthenticationFlow() {
        with(window) {
            val onboardingHost = authenticationHostViewController
            this.rootViewController = onboardingHost
        }
    }
}
