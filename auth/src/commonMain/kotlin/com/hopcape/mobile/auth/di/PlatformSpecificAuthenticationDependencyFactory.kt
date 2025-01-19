package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authenticator.HopcapeMobileAuthenticator
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactoryImpl
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.api.session.SessionManagerImpl
import com.hopcape.mobile.auth.api.storage.KeyValueStorage
import com.hopcape.mobile.auth.api.storage.LocalSettingsStorage
import com.hopcape.mobile.auth.api.storage.SecureStorage
import com.russhwolf.settings.Settings

/**
 * Abstract class that provides platform-specific implementations for authentication dependencies.
 * This factory is designed to be extended for different platforms such as Android and iOS,
 * providing necessary implementations for session management, storage, authentication,
 * and strategy creation.
 *
 * ## Example Usage:
 * To use this class in a platform-specific module:
 * ```kotlin
 * class MyPlatformAuthenticationDependencyFactory : PlatformSpecificAuthenticationDependencyFactory() {
 *     override fun createEncryptor(): Encryptor {
 *         // Platform-specific implementation for Encryptor
 *     }
 *
 *     override fun createDecrpytor(): Decryptor {
 *         // Platform-specific implementation for Decryptor
 *     }
 * }
 * ```
 *
 * @author Murtaza Khursheed
 */
abstract class PlatformSpecificAuthenticationDependencyFactory : AuthDependencyFactory {

    /**
     * Creates and returns a [SessionManager] instance for managing the user session.
     *
     * @return A [SessionManager] instance that handles session storage and retrieval.
     */
    override fun createSessionManager(): SessionManager {
        return SessionManagerImpl(
            storage = createStorage()
        )
    }

    /**
     * Creates and returns a [KeyValueStorage] instance, specifically a [SecureStorage],
     * which uses encryption and decryption for storing data securely.
     *
     * @return A [KeyValueStorage] instance that provides secure storage functionality.
     */
    override fun createStorage(): KeyValueStorage {
        return SecureStorage(
            encryptor = createEncryptor(),
            decryptor = createDecrpytor(),
            storage = LocalSettingsStorage(Settings())
        )
    }

    /**
     * Creates and returns an [Authenticator] instance that handles user authentication
     * through various methods like email, Google, Facebook, etc.
     *
     * @return An [Authenticator] instance that manages authentication processes.
     */
    override fun createAuthenticator(): Authenticator {
        return HopcapeMobileAuthenticator(
            sessionManager = createSessionManager()
        )
    }

    /**
     * Creates and returns an [AuthenticationStrategyFactory] instance that generates
     * different authentication strategies.
     *
     * @return An [AuthenticationStrategyFactory] instance for creating authentication strategies.
     */
    override fun createAuthenticationStrategyFactory(): AuthenticationStrategyFactory {
        return AuthenticationStrategyFactoryImpl()
    }
}
