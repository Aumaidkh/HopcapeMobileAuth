package com.hopcape.mobile.auth.api.session

import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.ID
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.storage.KeyValueStorage

/**
 * Manages user authentication sessions, handling storage and retrieval of session data.
 * This implementation persists the authenticated user details using a [KeyValueStorage] instance.
 *
 * ##### Usage Example:
 * ```kotlin
 * val storage: KeyValueStorage = SecureStorage(LocalSettingsStorage(Settings()), encryptor, decryptor)
 * val sessionManager: SessionManager = SessionManagerImpl(storage)
 *
 * val user = AuthenticatedUser(ID("123"), Email("user@example.com"), IDToken("jwt_token"))
 * sessionManager.storeSession(user) // Stores user session
 *
 * val currentUser = sessionManager.getCurrentSession() // Retrieves stored session
 * println(currentUser?.email?.value) // Output: user@example.com
 *
 * sessionManager.clearSession() // Logs out the user by clearing the session
 * ```
 *
 * @param storage The key-value storage mechanism used to persist session data.
 *
 * @author Murtaza Khursheed
 */
class SessionManagerImpl(
    private val storage: KeyValueStorage
) : SessionManager {

    /**
     * Stores the authenticated user in the session.
     *
     * @param user The authenticated user to store in the session.
     * @throws SessionException If there is an error while storing the session.
     */
    override fun storeSession(user: AuthenticatedUser) {
        with(user) {
            storage.put(USER_ID_KEY, id.value)
            storage.put(USER_EMAIL_KEY, email.value)
            storage.put(USER_ID_TOKEN_KEY, idToken.value)
        }
    }

    /**
     * Retrieves the currently authenticated user from the session.
     *
     * @return The current authenticated user, or `null` if no user is logged in.
     */
    override fun getCurrentSession(): AuthenticatedUser? {
        val userId = storage.get(USER_ID_KEY) ?: return null
        val userEmail = storage.get(USER_EMAIL_KEY) ?: return null
        val userIdToken = storage.get(USER_ID_TOKEN_KEY) ?: return null

        return AuthenticatedUser(
            id = ID(userId),
            email = Email(userEmail),
            idToken = IDToken(userIdToken)
        )
    }

    /**
     * Clears the current session, effectively logging out the user.
     *
     * @throws SessionException If there is an error while clearing the session.
     */
    override fun clearSession() {
        storage.clear()
    }

    companion object {
        private const val USER_SESSION_KEY = "user_session"
        private const val USER_ID_KEY = "user_id"
        private const val USER_EMAIL_KEY = "user_email"
        private const val USER_ID_TOKEN_KEY = "user_id_token"
    }
}
