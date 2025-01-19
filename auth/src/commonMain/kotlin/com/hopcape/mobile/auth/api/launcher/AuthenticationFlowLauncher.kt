package com.hopcape.mobile.auth.api.launcher

/**
 * Interface for launching the authentication flow.
 *
 * ###### Example usage:
 * ```
 * val authenticationFlowLauncher = AuthenticationFlowLauncher {
 *     // Implement authentication flow logic here, e.g., navigate to login screen
 *     println("Authentication flow started!")
 * }
 * authenticationFlowLauncher.launchAuthenticationFlow()
 * ```
 *
 * @author Murtaza Khursheed
 */
fun interface AuthenticationFlowLauncher {

    /**
     * Initiates and launches the authentication flow.
     * Implementations of this function should handle navigation and user interactions required for authentication.
     */
    fun launchAuthenticationFlow()
}
