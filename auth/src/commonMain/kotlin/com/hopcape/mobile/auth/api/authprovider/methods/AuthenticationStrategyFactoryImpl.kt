package com.hopcape.mobile.auth.api.authprovider.methods

import com.hopcape.mobile.auth.api.authprovider.strategy.AppleAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.AuthenticationStrategy
import com.hopcape.mobile.auth.data.remote.strategies.email.EmailPasswordAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.FacebookAuthenticationStrategy
import com.hopcape.mobile.auth.api.authprovider.strategy.GoogleAuthenticationStrategy
import com.hopcape.networking.api.client.NetworkingClient

/**
 * Factory class responsible for creating an [AuthenticationStrategy] based on the provided [AuthMethod].
 *
 * This class provides a centralized mechanism to create different authentication strategies such as Google,
 * Facebook, Apple, and Email-Password. It ensures that the appropriate strategy is instantiated based on the
 * desired authentication method.
 *
 * ### Key Features:
 * - Supports multiple authentication methods (e.g., Google, Facebook, Apple, Email-Password).
 * - Provides a unified interface for creating authentication strategies.
 * - Ensures separation of concerns by delegating strategy creation to this factory.
 *
 * @property networkingClient The [NetworkingClient] instance used for making HTTP requests in strategies like
 *                             [EmailPasswordAuthenticationStrategy].
 *
 * @constructor Creates an instance of [AuthenticationStrategyFactoryImpl] with the provided [NetworkingClient].
 *
 * #### Example Usage:
 * ```kotlin
 * val networkingClient = NetworkingClient.getInstance()
 * val authStrategyFactory = AuthenticationStrategyFactoryImpl(networkingClient)
 *
 * // Create a Google authentication strategy
 * val googleAuthStrategy = authStrategyFactory.createStrategy(AuthMethod.GOOGLE)
 * println("Created strategy: ${googleAuthStrategy::class.simpleName}")
 *
 * // Create an Email-Password authentication strategy
 * val emailPasswordAuthStrategy = authStrategyFactory.createStrategy(AuthMethod.EMAIL_PASSWORD)
 * println("Created strategy: ${emailPasswordAuthStrategy::class.simpleName}")
 * ```
 *
 * @author Murtaza Khursheed
 */
class AuthenticationStrategyFactoryImpl(
    private val networkingClient: NetworkingClient
) : AuthenticationStrategyFactory() {

    /**
     * Creates an [AuthenticationStrategy] based on the provided [AuthMethod] enum value.
     *
     * This method dynamically instantiates the appropriate authentication strategy based on the specified
     * authentication method. Supported strategies include:
     * - [GoogleAuthenticationStrategy] for [AuthMethod.GOOGLE]
     * - [FacebookAuthenticationStrategy] for [AuthMethod.FACEBOOK]
     * - [AppleAuthenticationStrategy] for [AuthMethod.APPLE]
     * - [EmailPasswordAuthenticationStrategy] for [AuthMethod.EMAIL_PASSWORD]
     *
     * @param method The authentication method for which the strategy is to be created.
     * @return An instance of the appropriate [AuthenticationStrategy].
     *
     * @throws IllegalArgumentException If the [method] is not recognized or supported.
     *
     * #### Example Usage:
     * ```kotlin
     * val networkingClient = NetworkingClient.getInstance()
     * val authStrategyFactory = AuthenticationStrategyFactoryImpl(networkingClient)
     *
     * // Create a Facebook authentication strategy
     * val facebookAuthStrategy = authStrategyFactory.createStrategy(AuthMethod.FACEBOOK)
     * println("Created strategy: ${facebookAuthStrategy::class.simpleName}")
     *
     * // Create an Apple authentication strategy
     * val appleAuthStrategy = authStrategyFactory.createStrategy(AuthMethod.APPLE)
     * println("Created strategy: ${appleAuthStrategy::class.simpleName}")
     * ```
     *
     * @see AuthenticationStrategy
     * @see AuthMethod
     */
    override fun createStrategy(method: AuthMethod): AuthenticationStrategy {
        return when (method) {
            AuthMethod.GOOGLE -> GoogleAuthenticationStrategy()
            AuthMethod.FACEBOOK -> FacebookAuthenticationStrategy()
            AuthMethod.APPLE -> AppleAuthenticationStrategy()
            AuthMethod.EMAIL_PASSWORD -> EmailPasswordAuthenticationStrategy(
                client = networkingClient
            )
            else -> throw IllegalArgumentException("Unsupported authentication method: $method")
        }
    }
}