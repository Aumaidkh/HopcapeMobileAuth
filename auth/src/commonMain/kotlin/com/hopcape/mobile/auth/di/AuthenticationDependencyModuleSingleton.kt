package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.api.storage.KeyValueStorage
import kotlin.reflect.KClass

/**
 * Singleton implementation of the [AuthenticationDependencyModule] which manages
 * the initialization and retrieval of authentication-related dependencies.
 *
 * The module initializes the dependencies via an [AuthDependencyFactory] and provides
 * access to them using the `get` function based on their class type.
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
 * ```
 *
 * @author Murtaza Khursheed
 */
object AuthenticationDependencyModuleSingleton : AuthenticationDependencyModule {

    private lateinit var dependencyGraph: Map<KClass<*>, Any>

    /**
     * Initializes the module with the provided [AuthDependencyFactory],
     * which is used to create and manage authentication-related dependencies.
     *
     * @param factory The [AuthDependencyFactory] instance that will be used to create dependencies.
     * @throws IllegalArgumentException If the factory provided is not valid or cannot be initialized.
     *
     * ## Example
     * ```kotlin
     * AuthenticationDependencyModuleSingleton.init(MyAuthDependencyFactory())
     * ```
     */
    override fun setFactory(factory: AuthDependencyFactory) {
        with(factory) {
            dependencyGraph = mapOf(
                Authenticator::class to createAuthenticator(),
                SessionManager::class to createSessionManager(),
                AuthenticationStrategyFactory::class to createAuthenticationStrategyFactory(),
                Encryptor::class to createEncryptor(),
                Decryptor::class to createDecrpytor(),
                KeyValueStorage::class to createStorage()
            )
        }
    }

    /**
     * Retrieves a dependency based on the provided class type.
     * This method allows retrieving any dependency registered in the module.
     *
     * @param clazz The class type of the dependency to retrieve.
     * @return The requested dependency, cast to the appropriate type.
     * @throws IllegalArgumentException If no dependency is found for the provided class type.
     *
     * ## Example
     * ```kotlin
     * val authenticator: Authenticator = AuthenticationDependencyModuleSingleton.get(Authenticator::class)
     * ```
     */
    override fun <T> get(clazz: KClass<*>): T {
        if (!::dependencyGraph.isInitialized) {
            throw IllegalStateException("Dependency graph not initialized")
        }
        return dependencyGraph[clazz] as? T
            ?: throw IllegalArgumentException("No dependency found for class $clazz")
    }
}
