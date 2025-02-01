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
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.data.repository.AuthRepositoryImpl
import com.hopcape.networking.api.client.NetworkingClient
import com.hopcape.networking.api.config.Configuration
import com.russhwolf.settings.Settings

/**
 * Abstract class that provides platform-specific implementations for authentication dependencies.
 *
 * This factory is designed to be extended for different platforms such as Android and iOS,
 * providing necessary implementations for session management, storage, encryption/decryption,
 * authentication, and strategy creation. It serves as a base class for creating platform-specific
 * dependency factories.
 *
 * ### Key Features:
 * - **Session Management**: Provides a [SessionManager] implementation for managing user sessions.
 * - **Secure Storage**: Uses [SecureStorage] for securely storing sensitive data.
 * - **Authentication**: Handles user authentication through an [Authenticator].
 * - **Strategy Creation**: Supports dynamic creation of authentication strategies via [AuthenticationStrategyFactory].
 * - **Networking**: Configures and provides a [NetworkingClient] for making HTTP requests.
 *
 * ## Example Usage:
 * To use this class in a platform-specific module:
 * ```kotlin
 * class MyPlatformAuthenticationDependencyFactory : PlatformSpecificAuthenticationDependencyFactory() {
 *     override fun createEncryptor(): Encryptor {
 *         // Platform-specific implementation for Encryptor
 *         return MyPlatformEncryptor()
 *     }
 *
 *     override fun createDecrpytor(): Decryptor {
 *         // Platform-specific implementation for Decryptor
 *         return MyPlatformDecryptor()
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
     * The [SessionManager] handles tasks such as storing and retrieving user session data,
     * clearing sessions on logout, and checking whether a user is currently authenticated.
     *
     * @return A [SessionManager] instance that handles session storage and retrieval.
     *
     * ##### Example Usage:
     * ```kotlin
     * val sessionManager = createSessionManager()
     * sessionManager.storeSession(user = authenticatedUser)
     * val currentUser = sessionManager.getCurrentUser()
     * println("Current User: ${currentUser?.email}")
     * ```
     *
     * @see SessionManager
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
     * The [SecureStorage] ensures that sensitive data, such as tokens or passwords, is securely stored
     * using platform-specific encryption mechanisms.
     *
     * @return A [KeyValueStorage] instance that provides secure storage functionality.
     *
     * ##### Example Usage:
     * ```kotlin
     * val storage = createStorage()
     * storage.putString("user_token", "abc123")
     * val token = storage.getString("user_token")
     * println("Stored Token: $token")
     * ```
     *
     * @see SecureStorage
     * @see KeyValueStorage
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
     * The [Authenticator] orchestrates the authentication process, including configuring settings,
     * initiating login, and handling success or failure callbacks.
     *
     * @return An [Authenticator] instance that manages authentication processes.
     *
     * ##### Example Usage:
     * ```kotlin
     * val authenticator = createAuthenticator()
     * authenticator.configure {
     *     setClientId("your-client-id")
     *     setAuthenticationFlowLauncher { /* Launch logic here */ }
     * }
     * authenticator.authenticate(
     *     onAuthenticationSuccess = { println("Authentication successful!") },
     *     onAuthenticationFailure = { println("Authentication failed!") }
     * )
     * ```
     *
     * @see Authenticator
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
     * The [AuthenticationStrategyFactory] dynamically creates strategies for different authentication methods,
     * such as email/password, OTP-based, or token-based authentication.
     *
     * @return An [AuthenticationStrategyFactory] instance for creating authentication strategies.
     *
     * ##### Example Usage:
     * ```kotlin
     * val strategyFactory = createAuthenticationStrategyFactory()
     * val strategy = strategyFactory.createStrategy(AuthMethod.EMAIL_PASSWORD)
     * val result = strategy.authenticate(credentials = AuthCredentials.EmailPassword(email, password))
     * result.fold(
     *     onSuccess = { user -> println("Authenticated User: ${user.email}") },
     *     onFailure = { exception -> println("Error: ${exception.message}") }
     * )
     * ```
     *
     * @see AuthenticationStrategyFactory
     */
    override fun createAuthenticationStrategyFactory(): AuthenticationStrategyFactory {
        return AuthenticationStrategyFactoryImpl(
            networkingClient = createNetworkingClient()
        )
    }

    /**
     * Creates and returns a [NetworkingClient] instance for making HTTP requests.
     *
     * The [NetworkingClient] provides a unified interface for interacting with remote APIs, handling tasks such as
     * sending requests, receiving responses, and managing errors.
     *
     * @return A [NetworkingClient] instance configured for use in the application.
     *
     * ##### Example Usage:
     * ```kotlin
     * val networkingClient = createNetworkingClient()
     * val response = networkingClient.makeRequest<String>(
     *     requestBuilder = {
     *         setUrl("https://api.example.com/data")
     *         setMethod(HttpMethod.GET)
     *         build()
     *     }
     * )
     * response.fold(
     *     onSuccess = { data -> println("Response Data: $data") },
     *     onFailure = { exception -> println("Error: ${exception.message}") }
     * )
     * ```
     *
     * @see NetworkingClient
     */
    override fun createNetworkingClient(): NetworkingClient {
        NetworkingClient.configure(
            configuration = Configuration(
                logger = {
                    println("HopcapeMobileAuth: $it")
                }
            )
        )
        return NetworkingClient.getInstance()
    }

    /**
     * Creates and returns an [AuthRepository] instance for handling authentication-related operations.
     *
     * The [AuthRepository] serves as a bridge between the data layer (e.g., network or local storage) and the domain
     * layer, providing methods for login, registration, logout, OTP handling, and session management.
     *
     * @return An [AuthRepository] instance that manages authentication operations.
     *
     * ##### Example Usage:
     * ```kotlin
     * val authRepository = createAuthRepository()
     * val result = authRepository.login(email = Email("user@example.com"), password = Password("securepassword123"))
     * result.fold(
     *     onSuccess = { user -> println("Welcome, ${user.email}!") },
     *     onFailure = { exception -> println("Error: ${exception.message}") }
     * )
     * ```
     *
     * @see AuthRepository
     */
    override fun createAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(createAuthenticationStrategyFactory(), createSessionManager())
    }
}