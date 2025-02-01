package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.api.storage.KeyValueStorage
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.networking.api.client.NetworkingClient
import kotlin.reflect.KClass

/**
 * Singleton implementation of the [AuthenticationDependencyModule] which manages
 * the initialization and retrieval of authentication-related dependencies.
 *
 * This module uses a dependency graph to store and retrieve instances of components
 * such as [Authenticator], [SessionManager], [Encryptor], and others. It ensures that
 * dependencies are initialized only once and provides thread-safe access to them.
 *
 * ## Key Features:
 * - **Lazy Initialization**: Dependencies are initialized only when needed.
 * - **Thread Safety**: Ensures safe access to the dependency graph in multi-threaded environments.
 * - **Type-Safe Retrieval**: Provides type-safe access to dependencies using Kotlin's reflection.
 * - **Error Handling**: Throws clear exceptions if dependencies are not initialized or not found.
 *
 * ## Example Usage
 * ```kotlin
 * // Initialize the module with a factory
 * AuthenticationDependencyModuleSingleton.init(MyAuthDependencyFactory())
 *
 * // Retrieve an Authenticator dependency
 * val authenticator: Authenticator = AuthenticationDependencyModuleSingleton.get(Authenticator::class)
 *
 * // Retrieve an Encryptor dependency
 * val encryptor: Encryptor = AuthenticationDependencyModuleSingleton.get(Encryptor::class)
 *
 * // Use the retrieved dependencies
 * authenticator.authenticate(
 *     onAuthenticationSuccess = { println("Authentication successful!") },
 *     onAuthenticationFailure = { println("Authentication failed!") }
 * )
 * ```
 *
 * @author Murtaza Khursheed
 */
object AuthenticationDependencyModuleSingleton : AuthenticationDependencyModule {

    /**
     * The dependency graph that stores all initialized dependencies.
     * This map associates each dependency's class type with its instance.
     */
    private lateinit var dependencyGraph: Map<KClass<*>, Any>

    /**
     * Initializes the module with the provided [AuthDependencyFactory],
     * which is used to create and manage authentication-related dependencies.
     *
     * This method should be called only once during the application's lifecycle.
     * Subsequent calls will overwrite the existing dependency graph.
     *
     * @param factory The [AuthDependencyFactory] instance that will be used to create dependencies.
     * @throws IllegalArgumentException If the factory provided is null or cannot initialize dependencies.
     *
     * ## Example
     * ```kotlin
     * AuthenticationDependencyModuleSingleton.init(MyAuthDependencyFactory())
     * ```
     */
    override fun setFactory(factory: AuthDependencyFactory) {
        dependencyGraph = mapOf(
            Authenticator::class to factory.createAuthenticator(),
            SessionManager::class to factory.createSessionManager(),
            AuthenticationStrategyFactory::class to factory.createAuthenticationStrategyFactory(),
            Encryptor::class to factory.createEncryptor(),
            Decryptor::class to factory.createDecrpytor(),
            KeyValueStorage::class to factory.createStorage(),
            AuthRepository::class to factory.createAuthRepository(),
            NetworkingClient::class to factory.createNetworkingClient()
        )
    }

    /**
     * Retrieves a dependency based on the provided class type.
     * This method allows retrieving any dependency registered in the module.
     *
     * @param clazz The class type of the dependency to retrieve.
     * @return The requested dependency, cast to the appropriate type.
     * @throws IllegalStateException If the dependency graph has not been initialized.
     * @throws IllegalArgumentException If no dependency is found for the provided class type.
     *
     * ## Example
     * ```kotlin
     * val authenticator: Authenticator = AuthenticationDependencyModuleSingleton.get(Authenticator::class)
     * val encryptor: Encryptor = AuthenticationDependencyModuleSingleton.get(Encryptor::class)
     * ```
     */
    override fun <T> get(clazz: KClass<*>): T {
        if (!::dependencyGraph.isInitialized) {
            throw IllegalStateException("Dependency graph not initialized. Call 'setFactory' first.")
        }

        return dependencyGraph[clazz] as? T
            ?: throw IllegalArgumentException("No dependency found for class $clazz")
    }
}