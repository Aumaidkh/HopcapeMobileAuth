package com.hopcape.mobile.auth.api.authenticator

import com.hopcape.mobile.auth.api.config.AuthConfig
import com.hopcape.mobile.auth.api.config.AuthConfigBuilder
import com.hopcape.mobile.auth.api.session.SessionManager

/**
 * A class that implements [Authenticator] to handle the authentication flow for the mobile application.
 * This class uses a [SessionManager] to manage the user session and provides methods to configure authentication settings
 * and initiate the authentication process.
 *
 * The authenticator requires a configuration to be set up before initiating the authentication process. The configuration
 * defines how the authentication flow should proceed, such as supported authentication methods and the authentication flow itself.
 *
 * ##### Example usage:
 *
 * **Configuring the Authenticator:**
 * ```kotlin
 * val authenticator = HopcapeMobileAuthenticator(sessionManager)
 * authenticator.configure {
 *     AuthConfigBuilder().apply {
 *         setClientId("your-client-id")
 *         setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
 *             override fun launchAuthenticationFlow() {
 *                 // Implement authentication launch logic here
 *             }
 *         })
 *     }
 * }
 * ```
 *
 * **Initiating the Authentication:**
 * ```kotlin
 * authenticator.authenticate(
 *     onAuthenticationSuccess = {
 *         // Handle successful authentication (e.g., navigate to the main screen)
 *     },
 *     onAuthenticationFailure = {
 *         // Handle failed authentication (e.g., show an error message)
 *     }
 * )
 * ```
 *
 * @param sessionManager The session manager responsible for managing the user session.
 *
 * @author Murtaza Khursheed
 */
internal class HopcapeMobileAuthenticator(
    private val sessionManager: SessionManager,
): Authenticator {

    /**
     * Configures the authenticator with the specified authentication settings.
     *
     * This method allows you to set up the authentication flow using an [AuthConfigBuilder].
     * You can define authentication providers, callbacks, and other settings through the builder.
     *
     * Example:
     * ```kotlin
     * authenticator.configure {
     *     AuthConfigBuilder().apply {
     *         setClientId("your-client-id")
     *         setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
     *             override fun launchAuthenticationFlow() {
     *                 // Implement authentication flow logic here
     *             }
     *         })
     *     }
     * }
     * ```
     *
     * @param config A lambda function that builds an [AuthConfig] using [AuthConfigBuilder].
     */
    override fun configure(config: AuthConfigBuilder.() -> AuthConfig) {
        Authenticator.config = config(AuthConfigBuilder())
    }

    override fun authenticate() {
        // If the user is already authenticated, trigger success callback
        if (sessionManager.getCurrentSession() != null) {
            Authenticator.config.onAuthSuccess()
            return
        }

        // If no session and authentication flow is configured, launch authentication flow
        Authenticator.config.authenticationFlowLauncher.launchAuthenticationFlow()
    }
}
