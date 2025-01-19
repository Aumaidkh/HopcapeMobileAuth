package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.api.storage.KeyValueStorage

/**
 * Factory interface responsible for creating dependencies required for authentication in the mobile app.
 * This includes components such as session management, encryption and decryption, storage, and authentication strategy.
 * Implementations of this factory should provide instances of these dependencies that are needed for the authentication flow.
 *
 * #### Example usage:
 * ```kotlin
 * val authDependencyFactory: AuthDependencyFactory = MyAuthDependencyFactory()
 * val sessionManager: SessionManager = authDependencyFactory.createSessionManager()
 * val encryptor: Encryptor = authDependencyFactory.createEncryptor()
 * val authenticator: Authenticator = authDependencyFactory.createAuthenticator()
 * ```
 *
 * This allows for better separation of concerns and makes the authentication system easier to test and maintain.
 *
 * @author Murtaza Khursheed
 */
interface AuthDependencyFactory {
    /**
     * Creates an instance of [SessionManager] responsible for managing user sessions.
     *
     * @return A new [SessionManager] instance.
     */
    fun createSessionManager(): SessionManager

    /**
     * Creates an instance of [Encryptor] used for encrypting sensitive data.
     *
     * @return A new [Encryptor] instance.
     */
    fun createEncryptor(): Encryptor

    /**
     * Creates an instance of [Decryptor] used for decrypting sensitive data.
     *
     * @return A new [Decryptor] instance.
     */
    fun createDecrpytor(): Decryptor

    /**
     * Creates an instance of [KeyValueStorage] for storing key-value pairs.
     *
     * @return A new [KeyValueStorage] instance.
     */
    fun createStorage(): KeyValueStorage

    /**
     * Creates an instance of [Authenticator] responsible for handling authentication flow.
     *
     * @return A new [Authenticator] instance.
     */
    fun createAuthenticator(): Authenticator

    /**
     * Creates an instance of [AuthenticationStrategyFactory] for creating authentication strategies.
     *
     * @return A new [AuthenticationStrategyFactory] instance.
     */
    fun createAuthenticationStrategyFactory(): AuthenticationStrategyFactory
}
