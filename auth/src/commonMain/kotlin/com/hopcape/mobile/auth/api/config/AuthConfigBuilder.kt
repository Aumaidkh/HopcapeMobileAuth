package com.hopcape.mobile.auth.api.config

import com.hopcape.mobile.auth.api.launcher.AuthenticationFlowLauncher

/**
 * Builder class for constructing an [AuthConfig] instance.
 *
 * ##### Example usage:
 * ```
 * val authConfig = AuthConfigBuilder()
 *     .setClientId("your-client-id")
 *     .setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             // Implement authentication launch logic here
 *         }
 *     })
 *     .build()
 * ```
 *
 * @author Murtaza Khursheed
 */
class AuthConfigBuilder {

    /**
     * Internal configuration instance that holds the authentication settings.
     */
    private var config = AuthConfig(
        authenticationFlowLauncher = {}
    )

    /**
     * Sets the client ID for authentication.
     *
     * @param clientId The unique identifier for the authentication client.
     * @return The updated [AuthConfigBuilder] instance.
     */
    fun setClientId(clientId: String) =
        apply {
            config = config.copy(clientId = clientId)
        }

    /**
     * Sets the authentication flow launcher.
     *
     * @param authenticationFlowLauncher The launcher responsible for initiating the authentication flow.
     * @return The updated [AuthConfigBuilder] instance.
     */
    fun setAuthenticationFlowLauncher(authenticationFlowLauncher: AuthenticationFlowLauncher) =
        apply {
            config = config.copy(
                authenticationFlowLauncher = authenticationFlowLauncher
            )
        }

    /**
     * Builds and returns the configured [AuthConfig] instance.
     *
     * @return The constructed [AuthConfig] object.
     */
    fun build() = config
}
