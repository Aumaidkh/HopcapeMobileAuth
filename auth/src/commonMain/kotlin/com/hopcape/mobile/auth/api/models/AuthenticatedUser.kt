package com.hopcape.mobile.auth.api.models

/**
 * Represents a user who has successfully authenticated.
 * This class holds the information about the authenticated user.
 *
 * It contains details such as the user's email, authentication token, and other profile information.
 *
 * ##### Example usage:
 *
 * **Creating an Authenticated User:**
 * ```kotlin
 * val authenticatedUser = AuthenticatedUser(
 *     id = ID("user-id"),
 *     email = Email("user@example.com"),
 *     idToken = IDToken("id-token-here"),
 *     username = "user123"
 * )
 * ```
 *
 * **Accessing User Properties:**
 * ```kotlin
 * val email = authenticatedUser.email.value
 * val idToken = authenticatedUser.idToken.value
 * val username = authenticatedUser.username
 * ```
 *
 * @property id The unique identifier for the authenticated user.
 * @property email The user's email address.
 * @property idToken The authentication token associated with the user.
 * @property username The username of the authenticated user (optional).
 *
 * @author Murtaza Khursheed
 */
data class AuthenticatedUser(
    val id: ID,
    val email: Email,
    val idToken: IDToken,
    val username: String? = null
)
