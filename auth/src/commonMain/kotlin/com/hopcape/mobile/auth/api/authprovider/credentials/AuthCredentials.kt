package com.hopcape.mobile.auth.api.authprovider.credentials

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.models.Password

/**
 * A sealed interface representing different types of authentication credentials.
 * This interface is used to store various credentials, such as email/password, Google, Facebook, and Apple tokens.
 * Implementations of this interface represent different credential types used in the authentication process.
 * Each credential type encapsulates the required information for authentication.
 *
 * ##### Example usage:
 *
 * **Email/Password Credentials:**
 * ```kotlin
 * val emailPasswordCredentials = AuthCredentials.EmailPassword(
 *     email = Email("user@example.com"),
 *     password = Password("password123")
 * )
 * ```
 *
 * **Google Credentials:**
 * ```kotlin
 * val googleCredentials = AuthCredentials.Google(
 *     idToken = IDToken("google-id-token-here")
 * )
 * ```
 *
 * **Facebook Credentials:**
 * ```kotlin
 * val facebookCredentials = AuthCredentials.Facebook(
 *     accessToken = IDToken("facebook-access-token-here")
 * )
 * ```
 *
 * **Apple Credentials:**
 * ```kotlin
 * val appleCredentials = AuthCredentials.Apple(
 *     idToken = IDToken("apple-id-token-here")
 * )
 * ```
 *
 * @see AuthCredentials.EmailPassword
 * @see AuthCredentials.Google
 * @see AuthCredentials.Facebook
 * @see AuthCredentials.Apple
 */
sealed interface AuthCredentials {

    /**
     * Email and password authentication credentials.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    data class EmailPassword(val email: Email, val password: Password) : AuthCredentials

    /**
     * Google authentication credentials.
     *
     * @param idToken The Google ID token.
     */
    data class Google(val idToken: IDToken) : AuthCredentials

    /**
     * Facebook authentication credentials.
     *
     * @param accessToken The Facebook access token.
     */
    data class Facebook(val accessToken: IDToken) : AuthCredentials

    /**
     * Apple authentication credentials.
     *
     * @param idToken The Apple ID token.
     */
    data class Apple(val idToken: IDToken) : AuthCredentials
}
