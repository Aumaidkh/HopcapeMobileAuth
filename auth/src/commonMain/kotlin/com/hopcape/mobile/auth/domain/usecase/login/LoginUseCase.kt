package com.hopcape.mobile.auth.domain.usecase.login

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.ValidationResult

/**
 * A use case responsible for handling the login process.
 *
 * This class implements [SuspendUseCase] to encapsulate the logic for validating user credentials
 * (email and password), performing the login operation via the [AuthRepository], and returning
 * a result of type [LoginResult]. It ensures that input validation is performed before attempting
 * to log in, and it handles both success and failure scenarios gracefully.
 *
 * Example usage:
 * ```kotlin
 * val authRepository = AuthRepository() // Assume this is implemented elsewhere
 * val loginUseCase = LoginUseCase(authRepository)
 *
 * suspend fun performLogin(email: String, password: String) {
 *     val params = LoginUseCase.Params(email, password)
 *     when (val result = loginUseCase(params)) {
 *         is LoginResult.Success -> println("Login successful: ${result.message}")
 *         is LoginResult.Error -> println("Login failed: ${result.errorMessage}")
 *         is LoginResult.ValidationError -> {
 *             if (result.emailError != null) println("Email error: ${result.emailError}")
 *             if (result.passwordError != null) println("Password error: ${result.passwordError}")
 *         }
 *     }
 * }
 *
 * // Example invocation
 * performLogin("user@example.com", "password123")
 * ```
 *
 * @property repository The [AuthRepository] instance used to perform the actual login operation.
 */
class LoginUseCase(
    private val repository: AuthRepository
) : SuspendUseCase<LoginUseCase.Params, LoginResult> {

    /**
     * Executes the login process with the given parameters.
     *
     * This function validates the email and password provided in [params]. If validation fails,
     * it returns a [LoginResult.ValidationError]. Otherwise, it attempts to log in using the
     * [AuthRepository] and returns either a [LoginResult.Success] or [LoginResult.Error]
     * depending on the outcome.
     *
     * Example usage:
     * ```kotlin
     * val params = LoginUseCase.Params("user@example.com", "password123")
     * val result = loginUseCase(params)
     * when (result) {
     *     is LoginResult.Success -> println("Logged in successfully!")
     *     is LoginResult.Error -> println("Login error: ${result.errorMessage}")
     *     is LoginResult.ValidationError -> println("Validation failed.")
     * }
     * ```
     *
     * @param params The input parameters containing the email and password for login.
     * @return A [LoginResult] representing the outcome of the login process.
     */
    override suspend fun invoke(params: Params): LoginResult {
        val emailResult = validateEmail(params.email)
        val passwordResult = validatePassword(params.password)
        if (!emailResult.isValid || !passwordResult.isValid) {
            return LoginResult.ValidationError(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
        }
        return repository.login(Email(params.email), Password(params.password)).fold(
            onSuccess = {
                LoginResult.Success("Logged in")
            },
            onFailure = {
                LoginResult.Error(it.message ?: "Unknown error")
            }
        )
    }

    /**
     * Validates the provided email address.
     *
     * This function uses the [Email] constructor to validate the email format. If the email
     * is invalid, an exception is thrown, and the error message is captured in the returned
     * [ValidationResult].
     *
     * Example usage:
     * ```kotlin
     * val emailResult = validateEmail("invalid-email")
     * if (!emailResult.isValid) {
     *     println("Email validation failed: ${emailResult.errorMessage}")
     * }
     * ```
     *
     * @param email The email address to validate.
     * @return A [ValidationResult] indicating whether the email is valid and any associated error message.
     */
    private fun validateEmail(email: String): ValidationResult {
        val error = kotlin.runCatching {
            Email(email)
        }.exceptionOrNull()
        return ValidationResult(
            isValid = error == null,
            errorMessage = error?.message
        )
    }

    /**
     * Validates the provided password.
     *
     * This function uses the [Password] constructor to validate the password format. If the password
     * is invalid, an exception is thrown, and the error message is captured in the returned
     * [ValidationResult].
     *
     * Example usage:
     * ```kotlin
     * val passwordResult = validatePassword("short")
     * if (!passwordResult.isValid) {
     *     println("Password validation failed: ${passwordResult.errorMessage}")
     * }
     * ```
     *
     * @param password The password to validate.
     * @return A [ValidationResult] indicating whether the password is valid and any associated error message.
     */
    private fun validatePassword(password: String): ValidationResult {
        val error = kotlin.runCatching {
            Password(password)
        }.exceptionOrNull()
        return ValidationResult(
            isValid = error == null,
            errorMessage = error?.message
        )
    }

    /**
     * Represents the input parameters required for the login process.
     *
     * This data class encapsulates the email and password provided by the user during the login attempt.
     *
     * Example usage:
     * ```kotlin
     * val params = LoginUseCase.Params("user@example.com", "securePassword123")
     * println("Email: ${params.email}, Password: ${params.password}")
     * ```
     *
     * @property email The email address entered by the user.
     * @property password The password entered by the user.
     */
    data class Params(
        /**
         * The email address entered by the user.
         *
         * Example value: `"user@example.com"`
         */
        val email: String,

        /**
         * The password entered by the user.
         *
         * Example value: `"securePassword123"`
         */
        val password: String
    )
}