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
 * authentication, and handling success or failure callbacks. It serves as the
 * primary entry point for integrating authentication functionality into an application.
 *
 * #### Key Features:
 * - **Configuration**: Allows setting up authentication settings such as client ID,
 *   authentication flow launcher, and API endpoints.
 * - **Authentication Flow**: Initiates the authentication process and provides
 *   callbacks for success and failure scenarios.
 * - **Dependency Injection**: Supports dependency injection for modular and testable code.
 *
 * #### Example Usage in Android Kotlin:
 * ```kotlin
 * val authenticator: Authenticator = Authenticator.create(MyAuthDependencyFactory())
 *
 * authenticator.configure {
 *     setClientId("your-client-id")
 *     setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             println("Launching authentication flow...")
 *         }
 *     })
 *     setEndPoints(
 *         EndPoints(
 *             loginEndpoint = Url("https://api.example.com/auth/login"),
 *             registerEndpoint = Url("https://api.example.com/auth/register"),
 *             requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
 *             verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
 *             googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
 *             facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
 *         )
 *     )
 * }
 *
 * authenticator.authenticate(
 *     onAuthenticationSuccess = {
 *         println("Authentication successful!")
 *     },
 *     onAuthenticationFailure = {
 *         println("Authentication failed!")
 *     }
 * )
 * ```
 *
 * #### Example Usage in Swift:
 * ```swift
 * let authenticator: Authenticator = Authenticator.create(MyAuthDependencyFactory())
 *
 * authenticator.configure { builder in
 *     builder.setClientId("your-client-id")
 *     builder.setAuthenticationFlowLauncher {
 *         print("Launching authentication flow...")
 *     }
 *     builder.setEndPoints(
 *         EndPoints(
 *             loginEndpoint: Url("https://api.example.com/auth/login"),
 *             registerEndpoint: Url("https://api.example.com/auth/register"),
 *             requestOtpEndpoint: Url("https://api.example.com/auth/request-otp"),
 *             verifyOtpEndpoint: Url("https://api.example.com/auth/verify-otp"),
 *             googleLoginEndpoint: Url("https://api.example.com/auth/google-login"),
 *             facebookLoginEndpoint: Url("https://api.example.com/auth/facebook-login")
 *         )
 *     )
 * }
 *
 * authenticator.authenticate(
 *     onAuthenticationSuccess: {
 *         print("Authentication successful!")
 *     },
 *     onAuthenticationFailure: {
 *         print("Authentication failed!")
 *     }
 * )
 * ```
 *
 * @author Murtaza Khursheed
 */
interface Authenticator {

    /**
     * Companion object for the [Authenticator] interface.
     *
     * This object provides utility methods for creating and configuring the authenticator.
     */
    companion object {

        /**
         * The global configuration for the authenticator.
         *
         * This property holds the [AuthConfig] instance that defines the authentication settings.
         * It must be initialized using the [configure] method before starting the authentication process.
         *
         * @throws UninitializedPropertyAccessException if accessed before being configured.
         */
        lateinit var config: AuthConfig

        /**
         * Creates an instance of the [Authenticator] using the provided [AuthDependencyFactory].
         *
         * This method sets up the dependency module and retrieves an [Authenticator] instance,
         * ensuring that all required dependencies are properly injected.
         *
         * @param factory The [AuthDependencyFactory] used for creating dependencies.
         *                This factory is responsible for providing implementations for the
         *                authentication components (e.g., network clients, storage).
         * @return The created [Authenticator] instance.
         *
         * ##### Example:
         * ```kotlin
         * val authenticator: Authenticator = Authenticator.create(MyAuthDependencyFactory())
         * ```
         */
        fun create(factory: AuthDependencyFactory): Authenticator {
            val dependencyModule: AuthenticationDependencyModule = AuthenticationDependencyModuleSingleton
            dependencyModule.setFactory(factory)
            return dependencyModule.get(Authenticator::class)
        }
    }

    /**
     * Configures the authenticator with the specified authentication settings.
     *
     * This method allows building an [AuthConfig] object through a lambda function
     * using [AuthConfigBuilder]. The configuration will be applied to the authenticator
     * and stored in the [config] property.
     *
     * @param config A lambda function that allows building an [AuthConfig] using [AuthConfigBuilder].
     *               This function provides a fluent API for setting up authentication settings.
     *
     * ##### Example:
     * ```kotlin
     * authenticator.configure {
     *     setClientId("your-client-id")
     *     setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
     *         override fun launchAuthenticationFlow() {
     *             println("Launching authentication flow...")
     *         }
     *     })
     *     setEndPoints(
     *         EndPoints(
     *             loginEndpoint = Url("https://api.example.com/auth/login"),
     *             registerEndpoint = Url("https://api.example.com/auth/register"),
     *             requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
     *             verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
     *             googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
     *             facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
     *         )
     *     )
     * }
     * ```
     *
     * @throws IllegalStateException if called after authentication has started.
     */
    fun configure(config: AuthConfigBuilder.() -> AuthConfig)


    fun authenticate()
}