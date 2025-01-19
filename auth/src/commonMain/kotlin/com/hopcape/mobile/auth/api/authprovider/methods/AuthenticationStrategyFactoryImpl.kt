package com.hopcape.mobile.auth.api.authprovider.methods

import com.hopcape.mobile.auth.api.authprovider.strategy.AppleAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.AuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.EmailPasswordAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.FacebookAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.GoogleAuthenticationStrategy

/**
 * Factory class responsible for creating an [AuthenticationStrategy] based on the provided [AuthMethod].
 *
 * This class provides a method to create different authentication strategies such as Google, Facebook,
 * Apple, and Email-Password based on the desired authentication method passed as a parameter.
 *
 * @constructor Creates an instance of [AuthenticationStrategyFactory].
 * @author Murtaza Khursheed
 *
 * @example
 * ```
 * val authStrategyFactory = AuthStrategyFactory()
 * val googleAuthStrategy = authStrategyFactory.createAuthenticationStrategy(AuthMethods.GOOGLE)
 * // The `googleAuthStrategy` is an instance of [GoogleAuthenticationStrategy].
 * ```
 */
class AuthenticationStrategyFactoryImpl: AuthenticationStrategyFactory() {

    /**
     * Creates an [AuthenticationStrategy] based on the provided [AuthMethod] enum value.
     *
     * The method will return the appropriate authentication strategy:
     * - [GoogleAuthenticationStrategy] for [AuthMethod.GOOGLE]
     * - [FacebookAuthenticationStrategy] for [AuthMethod.FACEBOOK]
     * - [AppleAuthenticationStrategy] for [AuthMethod.APPLE]
     * - [EmailPasswordAuthenticationStrategy] for [AuthMethod.EMAIL_PASSWORD]
     *
     * @param method The authentication method for which the strategy is to be created.
     * @return An instance of the appropriate [AuthenticationStrategy].
     * @throws IllegalArgumentException If the [method] is not recognized.
     */
    override fun createStrategy(method: AuthMethod): AuthenticationStrategy {
        return when (method) {
            AuthMethod.GOOGLE -> GoogleAuthenticationStrategy()
            AuthMethod.FACEBOOK -> FacebookAuthenticationStrategy()
            AuthMethod.APPLE -> AppleAuthenticationStrategy()
            AuthMethod.EMAIL_PASSWORD -> EmailPasswordAuthenticationStrategy()
        }
    }
}
