package com.hopcape.mobile.auth.presentation.screens.login

/**
 * Represents the possible user intents for the login screen.
 *
 * This sealed interface encapsulates all the actions that a user can perform on the login screen,
 * such as changing the email or password, initiating a login, or navigating to the registration screen.
 * It provides a type-safe way to handle user interactions in the UI layer.
 *
 * Example usage:
 * ```kotlin
 * fun handleIntent(intent: LoginScreenIntent) {
 *     when (intent) {
 *         is LoginScreenIntent.EmailChange -> {
 *             println("Email changed to: ${intent.value}")
 *         }
 *         is LoginScreenIntent.PasswordChange -> {
 *             println("Password changed to: ${intent.value}")
 *         }
 *         LoginScreenIntent.Login -> {
 *             println("Login button clicked")
 *         }
 *         LoginScreenIntent.Register -> {
 *             println("Register button clicked")
 *         }
 *     }
 * }
 *
 * // Example invocation
 * val emailChangeIntent = LoginScreenIntent.EmailChange("user@example.com")
 * val passwordChangeIntent = LoginScreenIntent.PasswordChange("password123")
 * val loginIntent = LoginScreenIntent.Login
 * val registerIntent = LoginScreenIntent.Register
 *
 * handleIntent(emailChangeIntent)
 * handleIntent(passwordChangeIntent)
 * handleIntent(loginIntent)
 * handleIntent(registerIntent)
 * ```
 */
sealed interface LoginScreenIntent {

    /**
     * Represents the intent to change the email input field.
     *
     * @property value The new value entered by the user in the email input field.
     *
     * Example usage:
     * ```kotlin
     * val emailChangeIntent = LoginScreenIntent.EmailChange("new.email@example.com")
     * println("Updated email: ${emailChangeIntent.value}") // Output: "Updated email: new.email@example.com"
     * ```
     */
    data class EmailChange(val value: String) : LoginScreenIntent

    /**
     * Represents the intent to change the password input field.
     *
     * @property value The new value entered by the user in the password input field.
     *
     * Example usage:
     * ```kotlin
     * val passwordChangeIntent = LoginScreenIntent.PasswordChange("securePassword123")
     * println("Updated password: ${passwordChangeIntent.value}") // Output: "Updated password: securePassword123"
     * ```
     */
    data class PasswordChange(val value: String) : LoginScreenIntent

    /**
     * Represents the intent to initiate the login process.
     *
     * This object is used when the user clicks the "Login" button on the login screen.
     *
     * Example usage:
     * ```kotlin
     * val loginIntent = LoginScreenIntent.Login
     * println("Login intent triggered") // Output: "Login intent triggered"
     * ```
     */
    data object Login : LoginScreenIntent

    /**
     * Represents the intent to navigate to the registration screen.
     *
     * This object is used when the user clicks the "Register" button on the login screen.
     *
     * Example usage:
     * ```kotlin
     * val registerIntent = LoginScreenIntent.Register
     * println("Register intent triggered") // Output: "Register intent triggered"
     * ```
     */
    data object Register : LoginScreenIntent
}