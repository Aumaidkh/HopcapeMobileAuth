package com.hopcape.mobile.auth.api.config

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
 *         loginEndpoint = Url("https://api.example.com/auth/login"),
 *         registerEndpoint = Url("https://api.example.com/auth/register"),
 *         requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
 *         verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
 *         googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
 *         facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
 *     )
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
     * ```
     * "your-client-id"
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
     * object : AuthenticationFlowLauncher {
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
     * EndPoints(
     *     loginEndpoint = Url("https://api.example.com/auth/login"),
     *     registerEndpoint = Url("https://api.example.com/auth/register"),
     *     requestOtpEndpoint = Url("https://api.example.com/auth/request-otp"),
     *     verifyOtpEndpoint = Url("https://api.example.com/auth/verify-otp"),
     *     googleLoginEndpoint = Url("https://api.example.com/auth/google-login"),
     *     facebookLoginEndpoint = Url("https://api.example.com/auth/facebook-login")
     * )
     * ```
     */
    val endPoints: EndPoints
)