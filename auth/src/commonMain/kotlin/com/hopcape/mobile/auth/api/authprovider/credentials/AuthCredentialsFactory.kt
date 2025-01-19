package com.hopcape.mobile.auth.api.authprovider.credentials

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.models.Password

/**
 * A factory class to build [AuthCredentials] instances.
 * This object provides methods to create different types of authentication credentials such as email/password,
 * Google, Facebook, and Apple credentials.
 * It helps in creating the required credential objects for various authentication methods.
 *
 * ##### Example usage:
 *
 * **Creating Email/Password Credentials:**
 * ```kotlin
 * val emailPasswordCredentials = AuthCredentialsFactory.createEmailPasswordCredentials(
 *     email = Email("user@example.com"),
 *     password = Password("password123")
 * )
 * ```
 *
 * **Creating Google Credentials:**
 * ```kotlin
 * val googleCredentials = AuthCredentialsFactory.createGoogleCredentials(
 *     idToken = IDToken("google-id-token-here")
 * )
 * ```
 *
 * **Creating Facebook Credentials:**
 * ```kotlin
 * val facebookCredentials = AuthCredentialsFactory.createFacebookCredentials(
 *     accessToken = IDToken("facebook-access-token-here")
 * )
 * ```
 *
 * **Creating Apple Credentials:**
 * ```kotlin
 * val appleCredentials = AuthCredentialsFactory.createAppleCredentials(
 *     idToken = IDToken("apple-id-token-here")
 * )
 * ```
 *
 * @author Murtaza Khursheed
 */
object AuthCredentialsFactory {

    /**
     * Creates an [AuthCredentials.EmailPassword] instance.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A new instance of [AuthCredentials.EmailPassword].
     */
    fun createEmailPasswordCredentials(email: Email, password: Password): AuthCredentials.EmailPassword {
        return AuthCredentials.EmailPassword(email, password)
    }

    /**
     * Creates an [AuthCredentials.Google] instance.
     *
     * @param idToken The Google ID token.
     * @return A new instance of [AuthCredentials.Google].
     */
    fun createGoogleCredentials(idToken: IDToken): AuthCredentials.Google {
        return AuthCredentials.Google(idToken)
    }

    /**
     * Creates an [AuthCredentials.Facebook] instance.
     *
     * @param accessToken The Facebook access token.
     * @return A new instance of [AuthCredentials.Facebook].
     */
    fun createFacebookCredentials(accessToken: IDToken): AuthCredentials.Facebook {
        return AuthCredentials.Facebook(accessToken)
    }

    /**
     * Creates an [AuthCredentials.Apple] instance.
     *
     * @param idToken The Apple ID token.
     * @return A new instance of [AuthCredentials.Apple].
     */
    fun createAppleCredentials(idToken: IDToken): AuthCredentials.Apple {
        return AuthCredentials.Apple(idToken)
    }
}
