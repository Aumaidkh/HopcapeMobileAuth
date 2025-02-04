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
import com.hopcape.mobile.auth.domain.usecase.login.LoginResult
import com.hopcape.mobile.auth.domain.usecase.login.LoginUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreenViewModel
import com.hopcape.mobile.auth.presentation.screens.utils.ViewModelFactory
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

    /**
     * Creates a generic [ViewModelFactory] that can be used to instantiate [ViewModel] instances
     * without requiring separate factories for each [ViewModel].
     *
     * This implementation registers factory functions for specific [ViewModel] types, such as
     * [LoginScreenViewModel], and resolves their dependencies dynamically. It avoids the need for
     * reflection or platform-specific features, making it suitable for Kotlin Multiplatform (KMP)
     * projects.
     *
     * #### Key Features:
     * - **Reusability**: A single factory instance can be used to create multiple [ViewModel] types.
     * - **Manual Dependency Injection**: Dependencies are explicitly registered and resolved,
     *   ensuring type safety and avoiding runtime errors.
     * - **Cross-Platform Compatibility**: Works seamlessly in KMP projects without relying on JVM-specific
     *   features like reflection.
     *
     * #### Example Usage:
     * ```kotlin
     * // Create the generic ViewModel factory
     * val viewModelFactory = createGenericViewModelFactory()
     *
     * // Resolve ViewModels
     * val loginViewModel = viewModelFactory.create<LoginScreenViewModel>()
     *
     * // Use the ViewModel in your app
     * loginViewModel.onIntent(LoginScreenIntent.EmailChange("user@example.com"))
     * ```
     *
     * @return A new instance of [ViewModelFactory] that can be used to create [ViewModel] instances
     *         by registering their factory functions.
     *
     * @see ViewModelFactory
     * @see LoginScreenViewModel
     */
    override fun createGenericViewModelFactory(): ViewModelFactory {
        val viewModelFactory = ViewModelFactory()
        viewModelFactory.register<LoginScreenViewModel> {
            LoginScreenViewModel(
                loginUseCase = LoginUseCase(createAuthRepository())
            )
        }
        return viewModelFactory
    }

    /**
     * Creates an instance of [SuspendUseCase] for handling the login functionality.
     *
     * This implementation provides a use case that encapsulates the business logic for authenticating users.
     * It takes user credentials as input and returns the result of the authentication process, such as success,
     * error, or validation failure. The use case relies on an [AuthRepository] to perform the actual login operation.
     *
     * #### Key Features:
     * - **Encapsulation**: Encapsulates the login logic, ensuring separation of concerns between
     *   the UI layer and the business logic.
     * - **Asynchronous Execution**: The use case operates as a suspending function, making it suitable
     *   for performing asynchronous operations like network requests or database queries.
     * - **Type Safety**: Uses strongly-typed parameters ([LoginUseCase.Params]) and results ([LoginResult])
     *   to ensure clarity and reduce runtime errors.
     *
     * #### Example Usage:
     * ```kotlin
     * // Create the login use case
     * val loginUseCase = createLoginUseCase()
     *
     * // Invoke the use case with user credentials
     * val result = runBlocking {
     *     loginUseCase(
     *         LoginUseCase.Params(
     *             email = "user@example.com",
     *             password = "securepassword123"
     *         )
     *     )
     * }
     *
     * // Handle the result
     * when (result) {
     *     is LoginResult.Success -> println("Login successful!")
     *     is LoginResult.Error -> println("Login failed: ${result.exception.message}")
     *     is LoginResult.ValidationError -> {
     *         println("Validation failed:")
     *         if (result.emailError != null) println("Email Error: ${result.emailError}")
     *         if (result.passwordError != null) println("Password Error: ${result.passwordError}")
     *     }
     * }
     * ```
     *
     * @return A new instance of [SuspendUseCase] that handles the login functionality.
     *
     * @see SuspendUseCase
     * @see LoginUseCase.Params
     * @see LoginResult
     * @see AuthRepository
     */
    override fun createLoginUseCase(): SuspendUseCase<LoginUseCase.Params, LoginResult> {
        return LoginUseCase(
            repository = createAuthRepository()
        )
    }
}