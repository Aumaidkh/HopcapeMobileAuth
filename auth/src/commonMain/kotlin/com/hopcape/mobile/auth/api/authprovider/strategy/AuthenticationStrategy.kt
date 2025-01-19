package com.hopcape.mobile.auth.api.authprovider.strategy

import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.models.AuthenticatedUser

/**
 * Represents a strategy for authenticating a user based on provided credentials.
 * Implementations of this interface define how to authenticate a user (e.g., using email/password, Google login, etc.).
 *
 * The strategy encapsulates the logic of handling authentication based on the provided credentials and returns a result indicating
 * whether authentication succeeded or failed, along with the authenticated user's information.
 *
 * ##### Example usage:
 *
 * **Defining an Authentication Strategy:**
 * ```kotlin
 * val emailPasswordStrategy = AuthenticationStrategy { credentials ->
 *     // Example logic to authenticate using email and password
 *     val user = authenticateWithEmailPassword(credentials as AuthCredentials.EmailPassword)
 *     if (user != null) {
 *         Result.success(user)
 *     } else {
 *         Result.failure(AuthenticationException("Authentication failed"))
 *     }
 * }
 * ```
 *
 * **Using the Authentication Strategy:**
 * ```kotlin
 * val credentials = AuthCredentials.EmailPassword(
 *     email = Email("user@example.com"),
 *     password = Password("password123")
 * )
 * val result = emailPasswordStrategy.authenticate(credentials)
 *
 * result.onSuccess { authenticatedUser ->
 *     // Handle successful authentication
 * }.onFailure { exception ->
 *     // Handle authentication failure
 * }
 * ```
 *
 * @see AuthCredentials
 * @see AuthenticatedUser
 *
 * @author Murtaza Khursheed
 */
fun interface AuthenticationStrategy {

    /**
     * Authenticates the user based on the provided credentials.
     *
     * @param credentials The credentials used to authenticate the user.
     * @return A [Result] containing the authenticated user if successful, or an error if authentication fails.
     */
    suspend fun authenticate(credentials: AuthCredentials): Result<AuthenticatedUser>
}
