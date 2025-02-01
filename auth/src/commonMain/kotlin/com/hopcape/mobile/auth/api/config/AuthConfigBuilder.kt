package com.hopcape.mobile.auth.api.config

import com.hopcape.mobile.auth.api.launcher.AuthenticationFlowLauncher
import com.hopcape.networking.api.Url

/**
 * Builder class for constructing an [AuthConfig] instance.
 *
 * This builder provides a fluent API for setting up authentication configurations step-by-step.
 * It allows you to configure properties such as the client ID, authentication flow launcher,
 * and endpoints in a structured manner before building the final [AuthConfig] object.
 *
 * ##### Example Usage:
 * ```kotlin
 * val authConfig = AuthConfigBuilder()
 *     .setClientId("your-client-id")
 *     .setAuthenticationFlowLauncher(object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             println("Launching authentication flow...")
 *         }
 *     })
 *     .setEndPoints(
 *         EndPoints(
 *             loginEndpoint = Url("https://api.example.com/auth/login"),
 *             registerEndpoint = Url("https://api.example.com/auth/register"),
 *             requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
 *             verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
 *             googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
 *             facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
 *         )
 *     )
 *     .build()
 *
 * // Accessing properties
 * println("Client ID: ${authConfig.clientId}")
 * authConfig.authenticationFlowLauncher.launchAuthenticationFlow()
 * println("Login Endpoint: ${authConfig.endPoints.loginEndpoint}")
 * ```
 *
 * @author Murtaza Khursheed
 */
class AuthConfigBuilder {

    /**
     * Internal configuration instance that holds the authentication settings.
     *
     * This mutable property is used to accumulate configuration values as they are set via the builder methods.
     */
    private var config = AuthConfig(
        clientId = "",
        authenticationFlowLauncher = object : AuthenticationFlowLauncher {
            override fun launchAuthenticationFlow() {
                throw UnsupportedOperationException("Authentication flow launcher not initialized.")
            }
        },
        endPoints = EndPoints(
            loginEndpoint = Url(""),
            registerEndpoint = Url(""),
            requestOtpEndpoint = Url(""),
            verifyOtpEndpoint = Url(""),
            googleLoginEndpoint = Url(""),
            facebookLoginEndpoint = Url("")
        )
    )

    /**
     * Sets the client ID for authentication.
     *
     * The client ID is a unique identifier provided by the authentication service provider.
     * It is used to identify the application during authentication requests.
     *
     * @param clientId The unique identifier for the authentication client.
     *                 Example: `"your-client-id"`
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setClientId(clientId: String) =
        apply {
            config = config.copy(clientId = clientId)
        }

    /**
     * Sets the authentication flow launcher.
     *
     * The authentication flow launcher is responsible for initiating the authentication process.
     * This could involve opening a login screen, redirecting to an OAuth provider, or triggering
     * a biometric prompt.
     *
     * @param authenticationFlowLauncher The launcher responsible for initiating the authentication flow.
     *                                    Example:
     *                                    ```kotlin
     *                                    object : AuthenticationFlowLauncher {
     *                                        override fun launchAuthenticationFlow() {
     *                                            println("Launching authentication flow...")
     *                                        }
     *                                    }
     *                                    ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setAuthenticationFlowLauncher(authenticationFlowLauncher: AuthenticationFlowLauncher) =
        apply {
            config = config.copy(authenticationFlowLauncher = authenticationFlowLauncher)
        }

    /**
     * Sets the collection of endpoints used for authentication-related API calls.
     *
     * These endpoints define the URLs for operations such as login, registration, OTP handling,
     * and social logins. Each endpoint corresponds to a specific API route that handles a particular
     * authentication action.
     *
     * @param endPoints The collection of endpoints for authentication-related API calls.
     *                  Example:
     *                  ```kotlin
     *                  EndPoints(
     *                      loginEndpoint = Url("https://api.example.com/auth/login"),
     *                      registerEndpoint = Url("https://api.example.com/auth/register"),
     *                      requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
     *                      verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
     *                      googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
     *                      facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
     *                  )
     *                  ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setEndPoints(endPoints: EndPoints) =
        apply {
            config = config.copy(endPoints = endPoints)
        }

    /**
     * Builds and returns the configured [AuthConfig] instance.
     *
     * This method finalizes the configuration process and returns the constructed [AuthConfig] object.
     * Ensure all required fields (e.g., client ID, authentication flow launcher, endpoints) are set
     * before calling this method.
     *
     * @return The constructed [AuthConfig] object.
     */
    fun build(): AuthConfig = config
}