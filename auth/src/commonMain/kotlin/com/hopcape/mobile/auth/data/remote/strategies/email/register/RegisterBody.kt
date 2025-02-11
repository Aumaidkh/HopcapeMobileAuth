package com.hopcape.mobile.auth.data.remote.strategies.email.register

import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request body for registering a new user via email.
 *
 * This data class encapsulates the required fields (email, password, and full name) for the registration
 * process. It is used to serialize the registration payload when sending it to the backend API.
 *
 * Example usage:
 * ```kotlin
 * val credentials = AuthCredentials.EmailPasswordRegistration(
 *     email = Email("john.doe@example.com"),
 *     password = Password("SecurePassword123!"),
 *     name = FullName("John Doe")
 * )
 * val registerBody = RegisterBody(credentials)
 * println(registerBody) // Output: RegisterBody(email=john.doe@example.com, password=SecurePassword123!, fullName=John Doe)
 * ```
 *
 * @author Murtaza Khursheed
 */
@Serializable
data class RegisterBody(
    /**
     * The email address of the user attempting to register.
     *
     * This field is serialized with the key "email" in the JSON payload sent to the backend.
     *
     * Example usage:
     * ```kotlin
     * val body = RegisterBody(email = "john.doe@example.com", password = "SecurePassword123!", fullName = "John Doe")
     * println(body.email) // Output: john.doe@example.com
     * ```
     */
    @SerialName("email")
    val email: String,

    /**
     * The password provided by the user for registration.
     *
     * This field is serialized with the key "password" in the JSON payload sent to the backend.
     *
     * Example usage:
     * ```kotlin
     * val body = RegisterBody(email = "john.doe@example.com", password = "SecurePassword123!", fullName = "John Doe")
     * println(body.password) // Output: SecurePassword123!
     * ```
     */
    @SerialName("password")
    val password: String,

    /**
     * The full name of the user attempting to register.
     *
     * This field is serialized with the key "fullName" in the JSON payload sent to the backend.
     *
     * Example usage:
     * ```kotlin
     * val body = RegisterBody(email = "john.doe@example.com", password = "SecurePassword123!", fullName = "John Doe")
     * println(body.fullName) // Output: John Doe
     * ```
     */
    @SerialName("fullName")
    val fullName: String
) {
    /**
     * Secondary constructor to create a [RegisterBody] instance from [AuthCredentials.EmailPasswordRegistration].
     *
     * This constructor simplifies the creation of a [RegisterBody] object by extracting the required fields
     * (email, password, and full name) from the provided credentials.
     *
     * Example usage:
     * ```kotlin
     * val credentials = AuthCredentials.EmailPasswordRegistration(
     *     email = Email("john.doe@example.com"),
     *     password = Password("SecurePassword123!"),
     *     name = FullName("John Doe")
     * )
     * val registerBody = RegisterBody(credentials)
     * println(registerBody) // Output: RegisterBody(email=john.doe@example.com, password=SecurePassword123!, fullName=John Doe)
     * ```
     *
     * @param credentials The registration credentials containing the user's email, password, and full name.
     */
    constructor(credentials: AuthCredentials.EmailPasswordRegistration) : this(
        email = credentials.email.value,
        password = credentials.password.value,
        fullName = credentials.name.value
    )
}