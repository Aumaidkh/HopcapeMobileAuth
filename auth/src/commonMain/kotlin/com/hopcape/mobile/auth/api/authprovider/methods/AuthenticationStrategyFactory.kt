package com.hopcape.mobile.auth.api.authprovider.methods

import com.hopcape.mobile.auth.api.authprovider.strategy.AuthenticationStrategy

/**
 * Abstract factory class for creating an [AuthenticationStrategy].
 *
 * This class serves as a blueprint for creating specific authentication strategies. Any subclass
 * of this class should implement the [createStrategy] method to provide a concrete implementation
 * of an [AuthenticationStrategy].
 *
 * @constructor Creates an instance of the [AuthenticationStrategyFactory].
 * @author Murtaza Khursheed
 *
 * @example
 * ```
 * class GoogleAuthStrategyFactory : AuthStrategyFactory() {
 *     override fun createStrategy(): AuthenticationStrategy {
 *         return GoogleAuthenticationStrategy()
 *     }
 * }
 *
 * val googleAuthFactory = GoogleAuthStrategyFactory()
 * val googleAuthStrategy = googleAuthFactory.createStrategy()
 * // The `googleAuthStrategy` is an instance of [GoogleAuthenticationStrategy].
 * ```
 */
abstract class AuthenticationStrategyFactory {
    /**
     * Abstract method for creating an instance of [AuthenticationStrategy].
     *
     * Subclasses should implement this method to provide their own specific [AuthenticationStrategy]
     * implementation.
     *
     * @return An instance of the appropriate [AuthenticationStrategy].
     */
    abstract fun createStrategy(method: AuthMethod): AuthenticationStrategy
}
