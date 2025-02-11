package com.hopcape.mobile.auth.domain.usecase.register

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.FullName
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.ValidationResult

/**
 * Use case responsible for registering a new user.
 *
 * This class encapsulates the business logic for validating user input (full name, email, password)
 * and performing the registration process via the [AuthRepository]. It adheres to the [SuspendUseCase]
 * interface, making it suitable for use in asynchronous workflows.
 *
 * Example usage:
 * ```kotlin
 * val registerUseCase = RegisterUseCase(authRepository)
 * val result = registerUseCase(
 *     RegisterUseCase.Params(
 *         fullName = "John Doe",
 *         email = "john.doe@example.com",
 *         password = "SecurePassword123!"
 *     )
 * )
 * when (result) {
 *     is RegisterResult.Success -> println("Registration succeeded: ${result.message}")
 *     is RegisterResult.ValidationError -> println("Validation failed: ${result.emailError}, ${result.passwordError}")
 *     is RegisterResult.Error -> println("Registration failed: ${result.errorMessage}")
 * }
 * ```
 *
 * @param repository The [AuthRepository] instance used to perform the registration.
 * @author Murtaza Khursheed
 */
class RegisterUseCase(
    private val repository: AuthRepository
) : SuspendUseCase<RegisterUseCase.Params, RegisterResult> {

    /**
     * Executes the registration process.
     *
     * This function validates the provided full name, email, and password. If validation succeeds,
     * it delegates the registration request to the [AuthRepository]. If any validation fails, it returns
     * a [RegisterResult.ValidationError] with detailed error messages.
     *
     * Example usage:
     * ```kotlin
     * val result = registerUseCase(
     *     RegisterUseCase.Params(
     *         fullName = "Jane Doe",
     *         email = "jane.doe@example.com",
     *         password = "Weak"
     *     )
     * )
     * when (result) {
     *     is RegisterResult.ValidationError -> println("Validation errors: ${result.fullNameError}, ${result.emailError}")
     *     is RegisterResult.Error -> println("Error during registration: ${result.errorMessage}")
     *     is RegisterResult.Success -> println("Registration successful!")
     * }
     * ```
     *
     * @param params The input parameters containing the user's full name, email, and password.
     * @return A [RegisterResult] indicating the outcome of the registration process.
     */
    override suspend fun invoke(params: Params): RegisterResult {
        val fullNameResult = validateFullName(params.fullName)
        val emailResult = validateEmail(params.email)
        val passwordResult = validatePassword(params.password)

        if (!emailResult.isValid || !passwordResult.isValid || !fullNameResult.isValid) {
            return RegisterResult.ValidationError(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                fullNameError = fullNameResult.errorMessage
            )
        }

        return repository.register(
            FullName(params.fullName),
            Email(params.email),
            Password(params.password)
        ).fold(
            onSuccess = { RegisterResult.Success("Registration Succeeded") },
            onFailure = { RegisterResult.Error(it.message ?: "Unknown error") }
        )
    }

    /**
     * Validates the provided full name.
     *
     * This function uses the [FullName] constructor to validate the format of the full name. If the
     * name is invalid, an exception is thrown, and the error message is captured in the returned
     * [ValidationResult].
     *
     * Example usage:
     * ```kotlin
     * val fullNameResult = validateFullName("John")
     * if (!fullNameResult.isValid) {
     *     println("Full name validation failed: ${fullNameResult.errorMessage}")
     * }
     * ```
     *
     * @param name The full name to validate.
     * @return A [ValidationResult] indicating whether the full name is valid and any associated error message.
     */
    private fun validateFullName(name: String): ValidationResult {
        val error = kotlin.runCatching {
            FullName(name)
        }.exceptionOrNull()
        return ValidationResult(
            isValid = error == null,
            errorMessage = error?.message
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
     * Represents the input parameters required for the registration process.
     *
     * @property email The user's email address.
     * @property password The user's password.
     * @property fullName The user's full name.
     */
    data class Params(
        val email: String,
        val password: String,
        val fullName: String
    )
}