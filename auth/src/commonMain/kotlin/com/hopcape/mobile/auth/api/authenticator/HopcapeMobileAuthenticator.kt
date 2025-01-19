package com.hopcape.mobile.auth.api.authenticator

import com.hopcape.mobile.auth.api.config.AuthConfig
import com.hopcape.mobile.auth.api.config.AuthConfigBuilder
import com.hopcape.mobile.auth.api.session.SessionManager

/**
 * A class that implements [Authenticator] to handle the authentication flow for the mobile application.
 * This class uses a [SessionManager] to manage the user session and provides a method to configure authentication settings
 * and initiate the authentication process.
 *
 * The authenticator requires a configuration to be set up before initiating the authentication process. The configuration
 * is used to define how the authentication flow should proceed (e.g., what authentication methods are supported).
 *
 * ##### Example usage:
 *
 * **Configuring the Authenticator:**
 * ```kotlin
 * val authenticator = HopcapeMobileAuthenticator(sessionManager)
 * authenticator.configure {
 *     AuthConfigBuilder().apply {
 *         // Configure authentication settings like providers, etc.
 *     }
 * }
 * ```
 *
 * **Initiating the Authentication:**
 * ```kotlin
 * authenticator.authenticate(
 *     onAuthenticationSuccess = {
 *         // Handle successful authentication (e.g., navigate to main screen)
 *     },
 *     onAuthenticationFailure = {
 *         // Handle failed authentication (e.g., show error message)
 *     }
 * )
 * ```
 *
 * @param sessionManager The session manager responsible for managing user session.
 *
 * @author Murtaza Khursheed
 */
internal class HopcapeMobileAuthenticator(
    private val sessionManager: SessionManager,
): Authenticator {

    private var authConfig: AuthConfig? = null

    /**
     * Configures the authenticator with the specified authentication settings.
     *
     * This method allows you to set up the authentication flow using an [AuthConfigBuilder].
     * You can define authentication providers, callbacks, and other settings using the builder.
     *
     * Example:
     * ```kotlin
     * authenticator.configure {
     *     AuthConfigBuilder().apply {
     *         // Set up the authentication configuration here
     *     }
     * }
     * ```
     *
     * @param config A lambda function that allows building an [AuthConfig] using [AuthConfigBuilder].
     */
    override fun configure(config: AuthConfigBuilder.() -> AuthConfig) {
        this.authConfig = config(AuthConfigBuilder())
    }

    /**
     * Initiates the authentication process.
     *
     * This method starts the authentication flow by launching the configured authentication flow.
     * If the user is already authenticated (i.e., the session exists), the success callback is invoked immediately.
     * If the authentication flow is not configured, an exception is thrown.
     *
     * Example:
     * ```kotlin
     * authenticator.authenticate(
     *     onAuthenticationSuccess = {
     *         // Handle successful authentication
     *     },
     *     onAuthenticationFailure = {
     *         // Handle failed authentication
     *     }
     * )
     * ```
     *
     * @param onAuthenticationSuccess A callback invoked when authentication is successful.
     * @param onAuthenticationFailure A callback invoked when authentication fails. Default is no-op.
     * @throws IllegalStateException If no authentication configuration is set.
     */
    override fun authenticate(
        onAuthenticationSuccess: () -> Unit,
        onAuthenticationFailure: () -> Unit
    ) {
        if (authConfig == null) {
            throw IllegalStateException("No auth config set. Please make sure to call configure first.")
        }
        // If the user is already authenticated, proceed to success callback
        if (sessionManager.getCurrentSession() != null) {
            onAuthenticationSuccess()
            return
        }

        // If no session and authentication flow is configured, launch the authentication flow
        authConfig?.authenticationFlowLauncher?.launchAuthenticationFlow()
            ?: throw IllegalStateException("No auth config set. Please make sure to call configure first.")
    }
}
