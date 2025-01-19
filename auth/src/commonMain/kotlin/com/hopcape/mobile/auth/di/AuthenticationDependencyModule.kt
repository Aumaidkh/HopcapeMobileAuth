package com.hopcape.mobile.auth.di

import kotlin.reflect.KClass

/**
 * Interface that defines a module for handling the initialization and retrieval
 * of dependencies related to authentication.
 *
 * The module is responsible for setting up an `AuthDependencyFactory` and providing
 * access to dependencies based on the provided class type.
 *
 * This interface ensures that dependencies required for authentication are correctly
 * initialized and can be accessed dynamically.
 *
 * ## Example Usage
 * ```kotlin
 * val authDependencyModule: AuthenticationDependencyModule = MyAuthenticationDependencyModule()
 *
 * // Initialize with a specific factory
 * authDependencyModule.setFactory(MyAuthDependencyFactory())
 *
 * // Retrieve a dependency by its class type
 * val authenticator: Authenticator = authDependencyModule.get(Authenticator::class) as Authenticator
 * ```
 *
 * ## Key Methods:
 * - `setFactory(factory: AuthDependencyFactory)`: Initializes the module with a provided factory for dependency creation.
 * - `get(clazz: KClass<*>)`: Retrieves a dependency by its class type.
 *
 * @author Murtaza Khursheed
 */
interface AuthenticationDependencyModule {

    /**
     * Initializes the module with the provided [AuthDependencyFactory],
     * which is used to create and manage authentication-related dependencies.
     *
     * This method configures the module with the necessary factory that handles
     * the creation and management of dependencies required for authentication.
     *
     * @param factory The [AuthDependencyFactory] instance that will be used to create dependencies.
     * @throws IllegalArgumentException If the factory provided is not valid or cannot be initialized.
     *
     * ## Example:
     * ```kotlin
     * authenticationDependencyModule.setFactory(MyAuthDependencyFactory())
     * ```
     */
    fun setFactory(factory: AuthDependencyFactory)

    /**
     * Retrieves a dependency based on the provided class type.
     * This method allows retrieving any dependency registered in the module.
     *
     * The method is generic, enabling access to dependencies of various types by passing their class type.
     *
     * @param clazz The class type of the dependency to retrieve.
     * @return The requested dependency, cast to the appropriate type.
     * @throws IllegalArgumentException If no dependency is found for the provided class type.
     *
     * ## Example:
     * ```kotlin
     * val authenticator: Authenticator = authenticationDependencyModule.get(Authenticator::class)
     * ```
     */
    fun <T> get(clazz: KClass<*>): T
}
