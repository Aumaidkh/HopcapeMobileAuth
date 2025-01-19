package com.hopcape.mobile.auth.api.authenticator

import com.hopcape.mobile.auth.api.config.AuthConfig
import com.hopcape.mobile.auth.api.config.AuthConfigBuilder
import com.hopcape.mobile.auth.di.AuthDependencyFactory
import com.hopcape.mobile.auth.di.AuthenticationDependencyModule
import com.hopcape.mobile.auth.di.AuthenticationDependencyModuleSingleton

/**
 * Interface for handling authentication in the mobile authentication library.
 *
 * This interface provides methods for configuring the authenticator, initiating
 * authentication, and handling success or failure callbacks.
 *
 * #### Example usage in Android Kotlin:
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
 * #### Example usage in Swift:
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

    companion object {
        var config: AuthConfig? = null

        /**
         * Creates an instance of the [Authenticator] using the provided [AuthDependencyFactory].
         *
         * This method sets up the dependency module and retrieves an [Authenticator]
         * instance, ensuring the proper dependencies are injected.
         *
         * @param factory The [AuthDependencyFactory] used for creating dependencies.
         * @return The created [Authenticator] instance.
         *
         * ## Example
         * ```kotlin
         * val authenticator: Authenticator = Authenticator.create(MyAuthDependencyFactory())
         * ```
         */
        fun create(
            factory: AuthDependencyFactory
        ): Authenticator {
            val dependencyModule: AuthenticationDependencyModule = AuthenticationDependencyModuleSingleton
            dependencyModule.setFactory(factory)
            return dependencyModule.get(Authenticator::class)
        }
    }

    /**
     * Configures the authenticator with the specified authentication settings.
     *
     * This method allows building an [AuthConfig] object through a lambda function
     * using [AuthConfigBuilder]. The configuration will be applied to the authenticator.
     *
     * @param config A lambda function that allows building an [AuthConfig] using [AuthConfigBuilder].
     *
     * ## Example
     * ```kotlin
     * authenticator.configure {
     *     setClientId("your-client-id")
     *     setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
     *         override fun launchAuthenticationFlow() {
     *             // Implement authentication launch logic here
     *         }
     *     })
     * }
     * ```
     */
    fun configure(config: AuthConfigBuilder.() -> AuthConfig)

    /**
     * Initiates the authentication process.
     *
     * This method triggers the authentication flow and executes the appropriate
     * callback based on the result.
     *
     * @param onAuthenticationSuccess A callback invoked when authentication is successful.
     * @param onAuthenticationFailure A callback invoked when authentication fails.
     *
     * ## Example
     * ```kotlin
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
     */
    fun authenticate(
        onAuthenticationSuccess: () -> Unit,
        onAuthenticationFailure: () -> Unit = {}
    )
}
