package com.hopcape.mobile.auth.api.config

import androidx.compose.material.MaterialTheme
import com.hopcape.mobile.auth.api.content.Content
import com.hopcape.mobile.auth.api.content.resources.DefaultStringResourceProvider
import com.hopcape.mobile.auth.api.content.LoginScreenContent
import com.hopcape.mobile.auth.api.launcher.AuthenticationFlowLauncher
import com.hopcape.networking.api.Url

/**
 * Builder class for constructing an [AuthConfig] instance.
 *
 * This builder provides a fluent API for setting up authentication configurations step-by-step.
 * It allows you to configure properties such as the client ID, authentication flow launcher,
 * endpoints, content, and theme in a structured manner before building the final [AuthConfig] object.
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
 *     .setOnAuthenticationSucceedListener {
 *         println("Authentication succeeded!")
 *     }
 *     .setOnAuthenticationFailureListener { error ->
 *         println("Authentication failed: ${error.message}")
 *     }
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
        authenticationFlowLauncher = { throw UnsupportedOperationException("Authentication flow launcher not initialized.") },
        endPoints = EndPoints(
            loginEndpoint = Url(""),
            registerEndpoint = Url(""),
            requestOtpEndpoint = Url(""),
            verifyOtpEndpoint = Url(""),
            googleLoginEndpoint = Url(""),
            facebookLoginEndpoint = Url("")
        ),
        content = Content(
            loginScreen = LoginScreenContent(DefaultStringResourceProvider())
        ),
        theme = MaterialTheme,
        onAuthSuccess = {},
        onAuthFailure = null
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
     * Sets the content configuration for the authentication screens.
     *
     * This method allows you to customize the textual and visual elements displayed on the
     * authentication screens, such as headings, labels, and placeholders.
     *
     * @param content The content configuration for the authentication screens.
     *                Example:
     *                ```kotlin
     *                Content(
     *                    loginScreen = LoginScreenContent(DefaultStringResourceProvider())
     *                )
     *                ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setContent(content: Content) =
        apply {
            config = config.copy(content = content)
        }

    /**
     * Sets the content configuration specifically for the login screen.
     *
     * This method allows you to customize the textual and visual elements displayed on the
     * login screen, such as the heading image, labels, and placeholders.
     *
     * @param content The content configuration for the login screen.
     *                Example:
     *                ```kotlin
     *                LoginScreenContent(DefaultStringResourceProvider())
     *                ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setLoginScreenContent(content: LoginScreenContent) =
        apply {
            config = config.copy(content = Content(loginScreen = content))
        }

    /**
     * Sets the callback function to be invoked when authentication succeeds.
     *
     * This function is executed after a successful authentication attempt. It can be used to
     * perform actions such as navigating to a new screen, displaying a success message, or
     * updating the UI.
     *
     * @param block The callback function to execute upon successful authentication.
     *              Example:
     *              ```kotlin
     *              {
     *                  println("Authentication succeeded!")
     *              }
     *              ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setOnAuthenticationSucceedListener(block: () -> Unit) =
        apply {
            config = config.copy(onAuthSuccess = block)
        }

    /**
     * Sets the callback function to be invoked when authentication fails.
     *
     * This function is executed if an error occurs during the authentication process. It receives
     * the exception that caused the failure, allowing you to handle errors gracefully (e.g., by
     * logging the error or displaying an error message to the user).
     *
     * @param block The callback function to execute upon authentication failure.
     *              Example:
     *              ```kotlin
     *              { error ->
     *                  println("Authentication failed: ${error.message}")
     *              }
     *              ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setOnAuthenticationFailureListener(block: (Throwable) -> Unit) =
        apply {
            config = config.copy(onAuthFailure = block)
        }

    /**
     * Sets the theme configuration for the authentication screens.
     *
     * This method allows you to define the visual theme of the authentication screens, such as
     * colors, fonts, and other styling elements. It uses Jetpack Compose's `MaterialTheme` to provide
     * a consistent look and feel across the application.
     *
     * @param theme The theme configuration for the authentication screens.
     *              Example:
     *              ```kotlin
     *              MaterialTheme(
     *                  colors = lightColors(),
     *                  typography = Typography(),
     *                  shapes = Shapes()
     *              )
     *              ```
     * @return The updated [AuthConfigBuilder] instance for method chaining.
     */
    fun setTheme(theme: MaterialTheme) =
        apply {
            config = config.copy(theme = theme)
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