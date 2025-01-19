package com.hopcape.mobile.auth.api.authenticator

import com.hopcape.mobile.auth.api.config.AuthConfig
import com.hopcape.mobile.auth.api.config.AuthConfigBuilder

/**
 * Interface for handling authentication in the mobile authentication library.
 *
 * ##### Example usage in Android Kotlin:
 * ```kotlin
 * val authenticator: Authenticator = MyAuthenticator() // Replace with your authenticator implementation
 * authenticator.configure {
 *     setClientId("your-client-id")
 *     setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             // Implement authentication launch logic here
 *         }
 *     })
 * }
 *
 * authenticator.authenticate(
 *     onAuthenticationSuccess = {
 *         // Handle success
 *         println("Authentication successful!")
 *     },
 *     onAuthenticationFailure = {
 *         // Handle failure
 *         println("Authentication failed!")
 *     }
 * )
 * ```
 *
 * ##### Example usage in Swift:
 * ```swift
 * let authenticator: Authenticator = MyAuthenticator() // Replace with your authenticator implementation
 * authenticator.configure { builder in
 *     builder.setClientId("your-client-id")
 *     builder.setAuthenticationFlowLauncher {
 *         // Implement authentication flow logic here
 *     }
 * }
 *
 * authenticator.authenticate(
 *     onAuthenticationSuccess: {
 *         // Handle success
 *         print("Authentication successful!")
 *     },
 *     onAuthenticationFailure: {
 *         // Handle failure
 *         print("Authentication failed!")
 *     }
 * )
 * ```
 *
 * @author Murtaza Khursheed
 */
interface Authenticator {

    /**
     * Configures the authenticator with the specified authentication settings.
     *
     * @param config A lambda function that allows building an [AuthConfig] using [AuthConfigBuilder].
     */
    fun configure(config: AuthConfigBuilder.() -> AuthConfig)

    /**
     * Initiates the authentication process.
     *
     * @param onAuthenticationSuccess A callback invoked when authentication is successful.
     * @param onAuthenticationFailure A callback invoked when authentication fails.
     */
    fun authenticate(
        onAuthenticationSuccess: () -> Unit,
        onAuthenticationFailure: () -> Unit = {}
    )
}
