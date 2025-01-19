package com.hopcape.mobile.auth.api.config

import com.hopcape.mobile.auth.api.launcher.AuthenticationFlowLauncher

/**
 * Configuration class for authentication settings.
 *
 * ##### Example usage:
 * ```
 * val authConfig = AuthConfig(
 *     clientId = "your-client-id",
 *     authenticationFlowLauncher = object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             // Implement authentication launch logic here
 *         }
 *     }
 * )
 * ```
 *
 * @property clientId The unique identifier for the authentication client.
 * @property authenticationFlowLauncher The launcher responsible for initiating the authentication flow.
 *
 * @author Murtaza Khursheed
 */
data class AuthConfig(
    val clientId: String = "",
    val authenticationFlowLauncher: AuthenticationFlowLauncher
)
