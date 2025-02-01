package com.hopcape.mobile.auth.data.remote.strategies.email

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response received from the server after a successful login request.
 *
 * This data class maps the JSON response returned by the authentication endpoint. It contains details about
 * the authenticated user, such as their unique identifier, email address, authentication token, and optional username.
 *
 * ### Key Features:
 * - **Serializable**: Annotated with `@Serializable` to enable JSON serialization/deserialization.
 * - **Immutable Properties**: All properties are immutable (`val`) to ensure thread safety and consistency.
 * - **Optional Fields**: Supports optional fields like `username` to handle cases where the server may not provide them.
 *
 * @property id The unique identifier of the authenticated user.
 * @property email The email address of the authenticated user.
 * @property idToken The authentication token issued by the server for the session.
 * @property username The username of the authenticated user (optional). Defaults to `null` if not provided by the server.
 *
 * #### Example Usage:
 * ```kotlin
 * // Simulating a JSON response from the server
 * val jsonResponse = """
 * {
 *     "id": "12345",
 *     "email": "user@example.com",
 *     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
 *     "username": "john_doe"
 * }
 * """
 *
 * // Deserialize the JSON response into a LoginResponse object
 * val loginResponse = Json.decodeFromString<LoginResponse>(jsonResponse)
 *
 * // Accessing properties
 * println("User ID: ${loginResponse.id}")          // Output: User ID: 12345
 * println("Email: ${loginResponse.email}")        // Output: Email: user@example.com
 * println("Token: ${loginResponse.idToken}")      // Output: Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
 * println("Username: ${loginResponse.username}")  // Output: Username: john_doe
 * ```
 *
 * @see kotlinx.serialization.Serializable
 */
@Serializable
data class LoginResponse(
    @SerialName("id")
    val id: String,

    @SerialName("email")
    val email: String,

    @SerialName("token")
    val idToken: String,

    @SerialName("username")
    val username: String? = null
)