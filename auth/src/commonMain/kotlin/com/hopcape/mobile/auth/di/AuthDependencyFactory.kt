package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.api.storage.KeyValueStorage
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.domain.usecase.login.LoginResult
import com.hopcape.mobile.auth.domain.usecase.login.LoginUseCase
import com.hopcape.mobile.auth.domain.usecase.register.RegisterResult
import com.hopcape.mobile.auth.domain.usecase.register.RegisterUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.presentation.screens.utils.ViewModelFactory
import com.hopcape.networking.api.client.NetworkingClient

/**
 * Factory interface responsible for creating dependencies required for authentication in the mobile app.
 *
 * This interface provides methods for creating instances of components such as session management,
 * encryption/decryption, storage, authentication flow handling, and networking. Implementations of this factory
 * should provide concrete implementations of these dependencies, enabling dependency injection and making the
 * authentication system modular, testable, and maintainable.
 *
 * #### Key Features:
 * - **Session Management**: Manages user sessions using [SessionManager].
 * - **Encryption/Decryption**: Handles sensitive data securely using [Encryptor] and [Decryptor].
 * - **Storage**: Provides key-value storage using [KeyValueStorage].
 * - **Authentication Flow**: Handles authentication logic using [Authenticator] and [AuthenticationStrategyFactory].
 * - **Networking**: Facilitates HTTP requests using [NetworkingClient].
 *
 * #### Example Usage:
 * ```kotlin
 * val authDependencyFactory: AuthDependencyFactory = MyAuthDependencyFactory()
 *
 * // Create a session manager
 * val sessionManager: SessionManager = authDependencyFactory.createSessionManager()
 *
 * // Create an encryptor and decryptor
 * val encryptor: Encryptor = authDependencyFactory.createEncryptor()
 * val decryptor: Decryptor = authDependencyFactory.createDecrpytor()
 *
 * // Create a key-value storage instance
 * val keyValueStorage: KeyValueStorage = authDependencyFactory.createStorage()
 *
 * // Create an authenticator
 * val authenticator: Authenticator = authDependencyFactory.createAuthenticator()
 *
 * // Create an authentication strategy factory
 * val strategyFactory: AuthenticationStrategyFactory = authDependencyFactory.createAuthenticationStrategyFactory()
 *
 * // Create a networking client
 * val networkingClient: NetworkingClient = authDependencyFactory.createNetworkingClient()
 *
 * // Create an authentication repository
 * val authRepository: AuthRepository = authDependencyFactory.createAuthRepository()
 * ```
 *
 * This modular approach allows for better separation of concerns and makes the authentication system easier to test and maintain.
 *
 * @author Murtaza Khursheed
 */
interface AuthDependencyFactory {

    /**
     * Creates an instance of [SessionManager] responsible for managing user sessions.
     *
     * The [SessionManager] handles tasks such as storing and retrieving user session data, clearing sessions on logout,
     * and checking whether a user is currently authenticated.
     *
     * @return A new [SessionManager] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val sessionManager: SessionManager = authDependencyFactory.createSessionManager()
     * sessionManager.storeSession(user = authenticatedUser)
     * val currentUser = sessionManager.getCurrentUser()
     * println("Current User: ${currentUser?.email}")
     * ```
     *
     * @see SessionManager
     */
    fun createSessionManager(): SessionManager

    /**
     * Creates an instance of [Encryptor] used for encrypting sensitive data.
     *
     * The [Encryptor] ensures that sensitive information, such as tokens or passwords, is securely encrypted before
     * being stored or transmitted.
     *
     * @return A new [Encryptor] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val encryptor: Encryptor = authDependencyFactory.createEncryptor()
     * val sensitiveData = "my-secret-data"
     * val encryptedData = encryptor.encrypt(sensitiveData)
     * println("Encrypted Data: $encryptedData")
     * ```
     *
     * @see Encryptor
     */
    fun createEncryptor(): Encryptor

    /**
     * Creates an instance of [Decryptor] used for decrypting sensitive data.
     *
     * The [Decryptor] complements the [Encryptor] by allowing the decryption of previously encrypted data.
     *
     * @return A new [Decryptor] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val decryptor: Decryptor = authDependencyFactory.createDecrpytor()
     * val encryptedData = "encrypted-secret-data"
     * val decryptedData = decryptor.decrypt(encryptedData)
     * println("Decrypted Data: $decryptedData")
     * ```
     *
     * @see Decryptor
     */
    fun createDecrpytor(): Decryptor

    /**
     * Creates an instance of [KeyValueStorage] for storing key-value pairs.
     *
     * The [KeyValueStorage] provides a simple mechanism for persisting small amounts of data, such as user preferences
     * or authentication tokens.
     *
     * @return A new [KeyValueStorage] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val storage: KeyValueStorage = authDependencyFactory.createStorage()
     * storage.putString("user_token", "abc123")
     * val token = storage.getString("user_token")
     * println("Stored Token: $token")
     * ```
     *
     * @see KeyValueStorage
     */
    fun createStorage(): KeyValueStorage

    /**
     * Creates an instance of [Authenticator] responsible for handling authentication flow.
     *
     * The [Authenticator] orchestrates the authentication process, including configuring settings, initiating login,
     * and handling success or failure callbacks.
     *
     * @return A new [Authenticator] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val authenticator: Authenticator = authDependencyFactory.createAuthenticator()
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
    fun createAuthenticator(): Authenticator

    /**
     * Creates an instance of [AuthenticationStrategyFactory] for creating authentication strategies.
     *
     * The [AuthenticationStrategyFactory] dynamically creates strategies for different authentication methods,
     * such as email/password, OTP-based, or token-based authentication.
     *
     * @return A new [AuthenticationStrategyFactory] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val strategyFactory: AuthenticationStrategyFactory = authDependencyFactory.createAuthenticationStrategyFactory()
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
    fun createAuthenticationStrategyFactory(): AuthenticationStrategyFactory

    /**
     * Creates an instance of [NetworkingClient] for making HTTP requests.
     *
     * The [NetworkingClient] provides a unified interface for interacting with remote APIs, handling tasks such as
     * sending requests, receiving responses, and managing errors.
     *
     * @return A new [NetworkingClient] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val networkingClient: NetworkingClient = authDependencyFactory.createNetworkingClient()
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
    fun createNetworkingClient(): NetworkingClient

    /**
     * Creates an instance of [AuthRepository] for handling authentication-related operations.
     *
     * The [AuthRepository] serves as a bridge between the data layer (e.g., network or local storage) and the domain
     * layer, providing methods for login, registration, logout, OTP handling, and session management.
     *
     * @return A new [AuthRepository] instance.
     *
     * ##### Example Usage:
     * ```kotlin
     * val authRepository: AuthRepository = authDependencyFactory.createAuthRepository()
     * val result = authRepository.login(email = Email("user@example.com"), password = Password("securepassword123"))
     * result.fold(
     *     onSuccess = { user -> println("Welcome, ${user.email}!") },
     *     onFailure = { exception -> println("Error: ${exception.message}") }
     * )
     * ```
     *
     * @see AuthRepository
     */
    fun createAuthRepository(): AuthRepository


    /**
     * Creates a generic [ViewModelFactory] that can be used to instantiate [ViewModel] instances
     * without requiring separate factories for each [ViewModel].
     *
     * This method simplifies dependency injection by providing a reusable factory that resolves
     * dependencies dynamically using a map of registered dependencies. It avoids the need for
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
     * // Define dependencies
     * val authRepository = AuthRepositoryImpl()
     * val sessionManager = SessionManagerImpl()
     *
     * // Create the generic ViewModel factory
     * val viewModelFactory = createGenericViewModelFactory()
     *
     * // Register factories for ViewModels
     * viewModelFactory.register<LoginScreenViewModel> {
     *     LoginScreenViewModel(authRepository = authRepository)
     * }
     * viewModelFactory.register<RegistrationScreenViewModel> {
     *     RegistrationScreenViewModel(sessionManager = sessionManager)
     * }
     *
     * // Resolve ViewModels
     * val loginViewModel = viewModelFactory.create<LoginScreenViewModel>()
     * val registrationViewModel = viewModelFactory.create<RegistrationScreenViewModel>()
     * ```
     *
     * @return A new instance of [ViewModelFactory] that can be used to create [ViewModel] instances
     *         by registering their factory functions.
     *
     * @see ViewModelFactory
     */
    fun createGenericViewModelFactory(): ViewModelFactory

    /**
     * Creates an instance of [SuspendUseCase] for handling the login functionality.
     *
     * This method provides a use case implementation that encapsulates the business logic for
     * authenticating users. It takes user credentials as input and returns the result of the
     * authentication process, such as success, error, or validation failure.
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
     */
    fun createLoginUseCase(): SuspendUseCase<LoginUseCase.Params, LoginResult>

    fun createRegisterUseCase(): SuspendUseCase<RegisterUseCase.Params, RegisterResult>
}