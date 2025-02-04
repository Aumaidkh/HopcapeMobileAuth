package com.hopcape.mobile.auth.api.config

import androidx.compose.material.MaterialTheme
import com.hopcape.mobile.auth.api.content.Content
import com.hopcape.mobile.auth.api.launcher.AuthenticationFlowLauncher

/**
 * Configuration class for authentication settings.
 *
 * This class encapsulates all the necessary configurations required for setting up
 * authentication in the application. It includes the client ID, a launcher for initiating
 * the authentication flow, and the endpoints for various authentication-related API calls.
 *
 * ##### Example Usage:
 * ```kotlin
 * val authConfig = AuthConfig(
 *     clientId = "your-client-id",
 *     authenticationFlowLauncher = object : AuthenticationFlowLauncher {
 *         override fun launchAuthenticationFlow() {
 *             // Implement authentication launch logic here
 *             println("Launching authentication flow...")
 *         }
 *     },
 *     endPoints = EndPoints(
 *         loginEndpoint = URL("https://api.example.com/auth/login"),
 *         registerEndpoint = URL("https://api.example.com/auth/register"),
 *         requestOtpEndpoint = URL("https://api.example.com/auth/request-otp"),
 *         verifyOtpEndpoint = URL("https://api.example.com/auth/verify-otp"),
 *         googleLoginEndpoint = URL("https://api.example.com/auth/google-login"),
 *         facebookLoginEndpoint = URL("https://api.example.com/auth/facebook-login")
 *     ),
 *     onAuthSuccess = { println("Authentication successful!") },
 *     onAuthFailure = { error -> println("Authentication failed: ${error.message}") },
 *     content = Content(title = "Welcome", description = "Please log in to continue."),
 *     theme = MaterialTheme()
 * )
 *
 * // Accessing properties
 * println("Client ID: ${authConfig.clientId}")
 * authConfig.authenticationFlowLauncher.launchAuthenticationFlow()
 * println("Login Endpoint: ${authConfig.endPoints.loginEndpoint}")
 * ```
 *
 * @author Murtaza Khursheed
 */
data class AuthConfig(
    /**
     * The unique identifier for the authentication client.
     *
     * This ID is typically provided by the authentication service provider and is used to identify
     * the application during authentication requests. It is often required for OAuth or other
     * authentication protocols.
     *
     * Example:
     * ```kotlin
     * val clientId = "your-client-id"
     * ```
     */
    val clientId: String = "",

    /**
     * The launcher responsible for initiating the authentication flow.
     *
     * This interface defines the method `launchAuthenticationFlow`, which should be implemented
     * to handle the logic for starting the authentication process. For example, this could involve
     * opening a login screen, redirecting to an OAuth provider, or triggering a biometric prompt.
     *
     * Example:
     * ```kotlin
     * val authenticationFlowLauncher = object : AuthenticationFlowLauncher {
     *     override fun launchAuthenticationFlow() {
     *         println("Launching authentication flow...")
     *     }
     * }
     * ```
     */
    val authenticationFlowLauncher: AuthenticationFlowLauncher,

    /**
     * The collection of endpoints used for authentication-related API calls.
     *
     * These endpoints define the URLs for operations such as login, registration, OTP handling,
     * and social logins. Each endpoint corresponds to a specific API route that handles a particular
     * authentication action.
     *
     * Example:
     * ```kotlin
     * val endPoints = EndPoints(
     *     loginEndpoint = URL("https://api.example.com/auth/login"),
     *     registerEndpoint = URL("https://api.example.com/auth/register"),
     *     requestOtpEndpoint = URL("https://api.example.com/auth/request-otp"),
     *     verifyOtpEndpoint = URL("https://api.example.com/auth/verify-otp"),
     *     googleLoginEndpoint = URL("https://api.example.com/auth/google-login"),
     *     facebookLoginEndpoint = URL("https://api.example.com/auth/facebook-login")
     * )
     * ```
     */
    val endPoints: EndPoints,

    /**
     * A callback function invoked when authentication succeeds.
     *
     * This function is executed after a successful authentication attempt. It can be used to
     * perform actions such as navigating to a new screen, displaying a success message, or
     * updating the UI.
     *
     * Example:
     * ```kotlin
     * val onAuthSuccess = { println("Authentication successful!") }
     * ```
     */
    val onAuthSuccess: () -> Unit = {},

    /**
     * A callback function invoked when authentication fails.
     *
     * This function is executed if an error occurs during the authentication process. It receives
     * the exception that caused the failure, allowing you to handle errors gracefully (e.g., by
     * logging the error or displaying an error message to the user).
     *
     * Example:
     * ```kotlin
     * val onAuthFailure = { error: Throwable ->
     *     println("Authentication failed: ${error.message}")
     * }
     * ```
     */
    val onAuthFailure: ((Throwable) -> Unit)? = null,

    /**
     * The content configuration for the authentication UI.
     *
     * This property defines the textual content displayed on the authentication screens, such as
     * titles, descriptions, or instructions. It allows for easy customization of the UI text.
     *
     * Example:
     * ```kotlin
     * val content = Content(
     *     title = "Welcome",
     *     description = "Please log in to continue."
     * )
     * ```
     */
    val content: Content,

    /**
     * The theme configuration for the authentication UI.
     *
     * This property defines the visual theme of the authentication screens, such as colors, fonts,
     * and other styling elements. It uses Jetpack Compose's `MaterialTheme` to provide a consistent
     * look and feel across the application.
     *
     * Example:
     * ```kotlin
     * val theme = MaterialTheme(
     *     colors = lightColors(),
     *     typography = Typography(),
     *     shapes = Shapes()
     * )
     * ```
     */
    val theme: MaterialTheme
)