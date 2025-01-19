package com.hopcape.mobile.auth.api.session

import com.hopcape.mobile.auth.api.models.AuthenticatedUser

/**
 * Manages the user session for the application, including storing, retrieving, and clearing the session.
 * This interface defines methods for managing the current authenticated user and session-related tasks.
 *
 * Implementations of this interface are responsible for handling the session's lifecycle, such as when the user is logged in,
 * logged out, or if the session needs to be refreshed.
 *
 * ##### Example usage:
 *
 * **Storing an Authenticated User in Session:**
 * ```kotlin
 * val user = AuthenticatedUser(
 *     id = ID("user-id"),
 *     email = Email("user@example.com"),
 *     idToken = IDToken("id-token-here")
 * )
 * sessionManager.storeSession(user)
 * ```
 *
 * **Retrieving the Current Session:**
 * ```kotlin
 * val currentUser = sessionManager.getCurrentSession()
 * if (currentUser != null) {
 *     // Handle the authenticated user
 * } else {
 *     // Handle the case when no user is authenticated
 * }
 * ```
 *
 * **Clearing the Session:**
 * ```kotlin
 * sessionManager.clearSession()
 * ```
 *
 * @author Murtaza Khursheed
 */
interface SessionManager {

    /**
     * Stores the authenticated user in the session.
     *
     * @param user The authenticated user to store in the session.
     * @throws SessionException If there is an error while storing the session.
     */
    fun storeSession(user: AuthenticatedUser)

    /**
     * Retrieves the currently authenticated user from the session.
     *
     * @return The current authenticated user, or `null` if no user is logged in.
     */
    fun getCurrentSession(): AuthenticatedUser?

    /**
     * Clears the current session, effectively logging out the user.
     *
     * @throws SessionException If there is an error while clearing the session.
     */
    fun clearSession()
}
