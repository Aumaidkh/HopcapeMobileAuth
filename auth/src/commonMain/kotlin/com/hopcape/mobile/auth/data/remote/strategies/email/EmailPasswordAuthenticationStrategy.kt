package com.hopcape.mobile.auth.data.remote.strategies.email

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.authprovider.strategy.AuthenticationStrategy
import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.ID
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.networking.api.client.NetworkingClient
import com.hopcape.networking.api.request.methods.HttpMethod

/**
 * An implementation of [AuthenticationStrategy] that authenticates users using their email and password.
 *
 * This strategy sends a POST request to a predefined authentication endpoint with the user's credentials
 * and processes the response to create an [AuthenticatedUser] object. It is designed to be used in scenarios
 * where email and password-based authentication is required.
 *
 * ### Key Features:
 * - Uses a [NetworkingClient] to handle HTTP requests.
 * - Maps the server response to an [AuthenticatedUser] object.
 * - Provides error handling through Kotlin's [Result] type.
 *
 * @property client The [NetworkingClient] instance used to make HTTP requests. Defaults to a singleton instance.
 *
 * ### Example Usage:
 * ```kotlin
 * import kotlinx.coroutines.runBlocking
 *
 * fun main() = runBlocking {
 *     // Step 1: Create an instance of EmailPasswordAuthenticationStrategy
 *     val authStrategy = EmailPasswordAuthenticationStrategy()
 *
 *     // Step 2: Prepare the user's credentials
 *     val credentials = AuthCredentials(
 *         email = "user@example.com",
 *         password = "securepassword123"
 *     )
 *
 *     // Step 3: Authenticate the user
 *     val result = authStrategy.authenticate(credentials)
 *
 *     // Step 4: Handle the result
 *     result.fold(
 *         onSuccess = { authenticatedUser ->
 *             println("Authentication Successful!")
 *             println("User ID: ${authenticatedUser.id}")
 *             println("Email: ${authenticatedUser.email}")
 *             println("ID Token: ${authenticatedUser.idToken}")
 *             println("Username: ${authenticatedUser.username}")
 *         },
 *         onFailure = { exception ->
 *             println("Authentication Failed: ${exception.message}")
 *         }
 *     )
 * }
 * ```
 *
 * @see AuthenticationStrategy
 * @see AuthenticatedUser
 * @see AuthCredentials
 */
internal class EmailPasswordAuthenticationStrategy(
    private val client: NetworkingClient = NetworkingClient.getInstance()
) : AuthenticationStrategy {

    /**
     * The URL of the authentication endpoint.
     *
     * This property retrieves the login endpoint URL from the global [Authenticator.config].
     * It uses a lazy delegate to ensure the URL is initialized only when accessed.
     *
     * @throws UninitializedPropertyAccessException If the [Authenticator.config] has not been initialized.
     */
    private val endPointUrl by lazy {
        Authenticator.config.endPoints.loginEndpoint
    }

    /**
     * Authenticates the user based on the provided credentials.
     *
     * This method sends a POST request to the authentication endpoint with the user's credentials.
     * If the request is successful, it maps the response to an [AuthenticatedUser] object.
     *
     * ### Request Details:
     * - **HTTP Method**: POST
     * - **Endpoint**: `/v1/authenticate`
     * - **Base URL**: Retrieved dynamically from [Authenticator.config.endPoints.loginEndpoint].
     *
     * ### Response Mapping:
     * The response from the server is expected to contain the following fields:
     * - `id`: A unique identifier for the user.
     * - `email`: The user's email address.
     * - `idToken`: A token representing the authenticated session.
     * - `username`: The user's username (optional).
     *
     * These fields are mapped to an [AuthenticatedUser] object.
     *
     * @param credentials The credentials used to authenticate the user. These typically include the user's email and password.
     * @return A [Result] containing the [AuthenticatedUser] if authentication is successful, or an error if authentication fails.
     *
     * @throws IllegalArgumentException If the credentials are invalid or missing required fields.
     * @throws IllegalStateException If the [Authenticator.config] has not been initialized.
     *
     * ### Example Usage:
     * ```kotlin
     * val authStrategy = EmailPasswordAuthenticationStrategy()
     * val credentials = AuthCredentials(email = "test@example.com", password = "password123")
     *
     * runBlocking {
     *     val result = authStrategy.authenticate(credentials)
     *     when {
     *         result.isSuccess -> {
     *             val user = result.getOrNull()
     *             println("Welcome, ${user?.username}!")
     *         }
     *         result.isFailure -> {
     *             println("Error: ${result.exceptionOrNull()?.message}")
     *         }
     *     }
     * }
     * ```
     *
     * @see AuthCredentials
     * @see AuthenticatedUser
     */
    override suspend fun authenticate(credentials: AuthCredentials): Result<AuthenticatedUser> {
        return client.makeRequest(
            responseType = LoginResponse::class,
            requestBuilder = {
                setUrl(endPointUrl)
                setMethod(HttpMethod.POST)
                build()
            }
        ).map {
            AuthenticatedUser(
                id = ID(it.id),
                email = Email(it.email),
                idToken = IDToken(it.idToken),
                username = it.username
            )
        }
    }
}