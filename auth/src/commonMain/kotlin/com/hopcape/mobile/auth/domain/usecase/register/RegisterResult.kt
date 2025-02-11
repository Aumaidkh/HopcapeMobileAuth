package com.hopcape.mobile.auth.domain.usecase.register


/**
 * Represents the possible outcomes of the login process.
 *
 * This sealed interface is used to encapsulate the result of a login operation,
 * providing a clear and type-safe way to handle success, errors, and validation issues.
 *
 * Example usage:
 * ```kotlin
 * fun handleLoginResult(loginResult: LoginResult) {
 *     when (loginResult) {
 *         is LoginResult.Success -> {
 *             // Handle successful login
 *             println("Login successful: ${loginResult.message}")
 *         }
 *         is LoginResult.Error -> {
 *             // Handle general error
 *             println("Login failed: ${loginResult.errorMessage}")
 *         }
 *         is LoginResult.ValidationError -> {
 *             // Handle validation errors
 *             if (loginResult.emailError != null) {
 *                 println("Email error: ${loginResult.emailError}")
 *             }
 *             if (loginResult.passwordError != null) {
 *                 println("Password error: ${loginResult.passwordError}")
 *             }
 *         }
 *     }
 * }
 *
 * // Example invocation
 * val result: LoginResult = LoginResult.Success("Welcome back!")
 * handleLoginResult(result)
 * ```
 */
sealed interface RegisterResult {

    /**
     * Indicates that the login process was successful.
     *
     * @property message A success message to display to the user.
     *
     * Example usage:
     * ```kotlin
     * val successResult = LoginResult.Success("You are now logged in.")
     * println(successResult.message) // Output: "You are now logged in."
     * ```
     */
    data class Success(val message: String) : RegisterResult

    /**
     * Indicates that an error occurred during the login process.
     *
     * @property errorMessage The error message describing the issue.
     *
     * Example usage:
     * ```kotlin
     * val errorResult = LoginResult.Error("Invalid credentials.")
     * println(errorResult.errorMessage) // Output: "Invalid credentials."
     * ```
     */
    data class Error(val errorMessage: String) : RegisterResult

    /**
     * Indicates that validation errors occurred during the login process.
     *
     * @property emailError An optional error message for the email input.
     *                      Null if there is no error related to the email.
     * @property passwordError An optional error message for the password input.
     *                         Null if there is no error related to the password.
     *
     * Example usage:
     * ```kotlin
     * val validationErrorResult = LoginResult.ValidationError(
     *     emailError = "Please enter a valid email.",
     *     passwordError = "Password must be at least 8 characters long."
     * )
     * if (validationErrorResult.emailError != null) {
     *     println(validationErrorResult.emailError) // Output: "Please enter a valid email."
     * }
     * if (validationErrorResult.passwordError != null) {
     *     println(validationErrorResult.passwordError) // Output: "Password must be at least 8 characters long."
     * }
     * ```
     */
    data class ValidationError(
        val emailError: String? = null,
        val passwordError: String? = null,
        val fullNameError: String? = null
    ) : RegisterResult
}