package com.hopcape.mobile.auth.data.repository

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.api.models.OTP
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling authentication-related operations.
 *
 * This interface provides methods for user login, registration, logout, OTP handling, and other common authentication
 * operations. It serves as a bridge between the data layer (e.g., network or local storage) and the domain layer,
 * ensuring separation of concerns and testability.
 *
 * ### Key Features:
 * - Supports multiple login mechanisms (e.g., email/password, token-based, OTP-based).
 * - Provides methods for user registration, password reset, OTP handling, and session management.
 * - Exposes observable state for session changes via [observeSession].
 *
 * ### Example Usage:
 * ```kotlin
 * import kotlinx.coroutines.runBlocking
 *
 * fun main() = runBlocking {
 *     val repository: AuthRepository = AuthRepositoryImpl() // Replace with your implementation
 *
 *     // Request OTP for a user's email
 *     val otpRequestResult = repository.requestOtp(email = Email("user@example.com"))
 *     otpRequestResult.fold(
 *         onSuccess = {
 *             println("OTP sent successfully to user@example.com")
 *         },
 *         onFailure = { exception ->
 *             println("Failed to send OTP: ${exception.message}")
 *         }
 *     )
 *
 *     // Verify OTP
 *     val otpVerifyResult = repository.verifyOtp(email = Email("user@example.com"), otp = OTP("123456"))
 *     otpVerifyResult.fold(
 *         onSuccess = { user ->
 *             println("OTP verified successfully! User ID: ${user.id}")
 *         },
 *         onFailure = { exception ->
 *             println("OTP verification failed: ${exception.message}")
 *         }
 *     )
 * }
 * ```
 */
interface AuthRepository {

    /**
     * Logs in a user using their email and password.
     *
     * This method sends a request to authenticate the user with the provided email and password.
     * If successful, it returns an [AuthenticatedUser] representing the authenticated user.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [Result] containing the [AuthenticatedUser] if login is successful, or an error if login fails.
     *
     * @throws IllegalArgumentException If the email or password is invalid or missing.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.login(email = Email("user@example.com"), password = Password("securepassword123"))
     * result.fold(
     *     onSuccess = { user ->
     *         println("Welcome, ${user.email}!")
     *     },
     *     onFailure = { exception ->
     *         println("Error: ${exception.message}")
     *     }
     * )
     * ```
     *
     * @see AuthenticatedUser
     */
    suspend fun login(email: Email, password: Password): Result<AuthenticatedUser>

    /**
     * Logs in a user using an ID token.
     *
     * This method authenticates the user using a pre-existing ID token, typically obtained from a previous
     * login or external authentication provider (e.g., OAuth). If successful, it returns an [AuthenticatedUser]
     * representing the authenticated user.
     *
     * @param token The ID token used for authentication.
     * @return A [Result] containing the [AuthenticatedUser] if login is successful, or an error if login fails.
     *
     * @throws IllegalArgumentException If the token is invalid or expired.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.login(token = IDToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))
     * result.fold(
     *     onSuccess = { user ->
     *         println("Logged in with token. User ID: ${user.id}")
     *     },
     *     onFailure = { exception ->
     *         println("Token login failed: ${exception.message}")
     *     }
     * )
     * ```
     *
     * @see AuthenticatedUser
     */
    suspend fun login(token: IDToken): Result<AuthenticatedUser>

    /**
     * Registers a new user with the provided email and password.
     *
     * This method sends a request to create a new user account with the given credentials. If successful,
     * it returns an [AuthenticatedUser] representing the newly registered user.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [Result] containing the [AuthenticatedUser] if registration is successful, or an error if registration fails.
     *
     * @throws IllegalArgumentException If the email or password is invalid or missing.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.register(email = Email("newuser@example.com"), password = Password("newpassword123"))
     * result.fold(
     *     onSuccess = { user ->
     *         println("Registration Successful! User ID: ${user.id}")
     *     },
     *     onFailure = { exception ->
     *         println("Registration Failed: ${exception.message}")
     *     }
     * )
     * ```
     *
     * @see AuthenticatedUser
     */
    suspend fun register(email: Email, password: Password): Result<AuthenticatedUser>

    /**
     * Sends a password reset request for the specified email address.
     *
     * This method triggers a password reset flow by sending an email with instructions to reset the password.
     * It does not return any user-specific data but indicates whether the request was successful.
     *
     * @param email The user's email address for which the password reset is requested.
     * @return A [Result] indicating success or failure of the password reset request.
     *
     * @throws IllegalArgumentException If the email is invalid or missing.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.resetPassword(email = Email("user@example.com"))
     * result.fold(
     *     onSuccess = {
     *         println("Password reset email sent successfully.")
     *     },
     *     onFailure = { exception ->
     *         println("Failed to send password reset email: ${exception.message}")
     *     }
     * )
     * ```
     */
    suspend fun resetPassword(email: Email): Result<Unit>

    /**
     * Requests a One-Time Password (OTP) for the specified email address.
     *
     * This method sends an OTP to the user's email or phone number (depending on the implementation) for
     * verification purposes. It is commonly used in multi-factor authentication (MFA) or passwordless login flows.
     *
     * @param email The user's email address for which the OTP is requested.
     * @return A [Result] indicating success or failure of the OTP request.
     *
     * @throws IllegalArgumentException If the email is invalid or missing.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.requestOtp(email = Email("user@example.com"))
     * result.fold(
     *     onSuccess = {
     *         println("OTP sent successfully to user@example.com")
     *     },
     *     onFailure = { exception ->
     *         println("Failed to send OTP: ${exception.message}")
     *     }
     * )
     * ```
     */
    suspend fun requestOtp(email: Email): Result<Unit>

    /**
     * Verifies a One-Time Password (OTP) for the specified email address.
     *
     * This method validates the OTP provided by the user. If the OTP is valid, it returns an [AuthenticatedUser]
     * representing the authenticated user. This is commonly used in multi-factor authentication (MFA) or
     * passwordless login flows.
     *
     * @param email The user's email address for which the OTP is being verified.
     * @param otp The OTP provided by the user.
     * @return A [Result] containing the [AuthenticatedUser] if OTP verification is successful, or an error if verification fails.
     *
     * @throws IllegalArgumentException If the email or OTP is invalid or missing.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.verifyOtp(email = Email("user@example.com"), otp = OTP("123456"))
     * result.fold(
     *     onSuccess = { user ->
     *         println("OTP verified successfully! User ID: ${user.id}")
     *     },
     *     onFailure = { exception ->
     *         println("OTP verification failed: ${exception.message}")
     *     }
     * )
     * ```
     *
     * @see AuthenticatedUser
     */
    suspend fun verifyOtp(email: Email, otp: OTP): Result<AuthenticatedUser>

    /**
     * Logs out the currently authenticated user.
     *
     * This method clears the user's session and invalidates any active tokens. It does not return any data
     * but indicates whether the logout process was successful.
     *
     * @return A [Result] indicating success or failure of the logout process.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * val result = repository.logout()
     * result.fold(
     *     onSuccess = {
     *         println("User logged out successfully.")
     *     },
     *     onFailure = { exception ->
     *         println("Logout failed: ${exception.message}")
     *     }
     * )
     * ```
     */
    suspend fun logout(): Result<Unit>

    /**
     * Observes the current session state of the user.
     *
     * This method provides a reactive stream of the user's session state, allowing the application to react
     * to changes such as login, logout, or token expiration. The returned [Flow] emits the latest [AuthenticatedUser]
     * when the user is authenticated or `null` when the user is logged out.
     *
     * @return A [Flow] emitting the current [AuthenticatedUser] or `null` based on the session state.
     *
     * ### Example Usage:
     * ```kotlin
     * val repository: AuthRepository = AuthRepositoryImpl()
     * repository.observeSession().collect { user ->
     *     if (user != null) {
     *         println("User is logged in. Email: ${user.email}")
     *     } else {
     *         println("User is logged out.")
     *     }
     * }
     * ```
     *
     * @see AuthenticatedUser
     */
    fun observeSession(): Flow<AuthenticatedUser?>
}