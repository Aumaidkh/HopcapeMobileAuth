package com.hopcape.mobile.auth.presentation.screens.utils

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

/**
 * A type-safe [ViewModelFactory] that resolves dependencies manually.
 *
 * This factory allows you to register [ViewModel] creation logic for each [ViewModel] type
 * without relying on reflection. It ensures compatibility with Kotlin Multiplatform (KMP)
 * projects by avoiding JVM-specific features like reflection or `Class`.
 *
 * #### Key Features:
 * - **Type Safety**: Uses Kotlin's reified type parameters to ensure type-safe registration and resolution.
 * - **Manual Dependency Injection**: Requires explicit registration of factories for each [ViewModel].
 * - **Cross-Platform Compatibility**: Works seamlessly in KMP projects without relying on reflection.
 *
 * #### Example Usage:
 * ```kotlin
 * // Create the factory
 * val viewModelFactory = ViewModelFactory()
 *
 * // Register factories for ViewModels
 * viewModelFactory.register<LoginScreenViewModel> {
 *     LoginScreenViewModel(authRepository = authDependencyFactory.createAuthRepository())
 * }
 * viewModelFactory.register<RegistrationScreenViewModel> {
 *     RegistrationScreenViewModel(sessionManager = authDependencyFactory.createSessionManager())
 * }
 *
 * // Resolve ViewModels
 * val loginViewModel = viewModelFactory.create<LoginScreenViewModel>()
 * val registrationViewModel = viewModelFactory.create<RegistrationScreenViewModel>()
 * ```
 */
class ViewModelFactory {

    // Internal map to store factory functions for each ViewModel type
    val factories = mutableMapOf<KClass<*>, () -> ViewModel>()

    /**
     * Registers a factory function for creating instances of the specified [ViewModel] type.
     *
     * @param factory A lambda function that creates an instance of the [ViewModel].
     */
    inline fun <reified T : ViewModel> register(noinline factory: () -> T) {
        factories[T::class] = factory
    }

    /**
     * Creates an instance of the specified [ViewModel] type.
     *
     * @return A new instance of the requested [ViewModel].
     * @throws IllegalArgumentException If no factory is registered for the requested [ViewModel] type.
     */
    inline fun <reified T : ViewModel> create(): T {
        val factory = factories[T::class]
            ?: throw IllegalArgumentException("No factory registered for ${T::class.simpleName}")
        @Suppress("UNCHECKED_CAST")
        return factory() as T
    }
}