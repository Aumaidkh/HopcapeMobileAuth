package com.hopcape.mobile.auth.presentation.screens.register

/**
 * Represents user intents for the registration screen.
 *
 * This sealed interface encapsulates all possible user actions on the registration screen, such as
 * changing input fields, triggering registration, or navigating back to the login screen. It is used
 * by the [RegisterScreenViewModel] to handle user interactions and update the UI state accordingly.
 *
 * Example usage:
 * ```kotlin
 * val intent = RegisterScreenIntent.EmailChange("john.doe@example.com")
 * when (intent) {
 *     is RegisterScreenIntent.Login -> println("User wants to navigate to the login screen.")
 *     is RegisterScreenIntent.FullNameChange -> println("Full name changed to: ${intent.value}")
 *     is RegisterScreenIntent.EmailChange -> println("Email changed to: ${intent.value}")
 *     is RegisterScreenIntent.PasswordChange -> println("Password changed to: ${intent.value}")
 *     is RegisterScreenIntent.Register -> println("User triggered registration.")
 * }
 * ```
 *
 * @author Murtaza Khursheed
 */
sealed interface RegisterScreenIntent {

    /**
     * Represents the intent to navigate back to the login screen.
     *
     * This intent is triggered when the user chooses to return to the login screen without completing
     * the registration process.
     *
     * Example usage:
     * ```kotlin
     * val intent = RegisterScreenIntent.Login
     * when (intent) {
     *     is RegisterScreenIntent.Login -> println("Navigating to login screen.")
     * }
     * ```
     */
    data object Login : RegisterScreenIntent

    /**
     * Represents the intent to change the full name input field.
     *
     * This intent is triggered when the user updates the full name field in the UI. The new value
     * is captured in the `value` property.
     *
     * Example usage:
     * ```kotlin
     * val intent = RegisterScreenIntent.FullNameChange("John Doe")
     * println("Full name changed to: ${intent.value}") // Output: John Doe
     * ```
     *
     * @property value The new value entered by the user for the full name field.
     */
    data class FullNameChange(val value: String) : RegisterScreenIntent

    /**
     * Represents the intent to change the email input field.
     *
     * This intent is triggered when the user updates the email field in the UI. The new value
     * is captured in the `value` property.
     *
     * Example usage:
     * ```kotlin
     * val intent = RegisterScreenIntent.EmailChange("john.doe@example.com")
     * println("Email changed to: ${intent.value}") // Output: john.doe@example.com
     * ```
     *
     * @property value The new value entered by the user for the email field.
     */
    data class EmailChange(val value: String) : RegisterScreenIntent

    /**
     * Represents the intent to change the password input field.
     *
     * This intent is triggered when the user updates the password field in the UI. The new value
     * is captured in the `value` property.
     *
     * Example usage:
     * ```kotlin
     * val intent = RegisterScreenIntent.PasswordChange("SecurePassword123!")
     * println("Password changed to: ${intent.value}") // Output: SecurePassword123!
     * ```
     *
     * @property value The new value entered by the user for the password field.
     */
    data class PasswordChange(val value: String) : RegisterScreenIntent

    /**
     * Represents the intent to trigger the registration process.
     *
     * This intent is triggered when the user submits the registration form, indicating that they want
     * to proceed with the registration process.
     *
     * Example usage:
     * ```kotlin
     * val intent = RegisterScreenIntent.Register
     * when (intent) {
     *     is RegisterScreenIntent.Register -> println("Registration triggered.")
     * }
     * ```
     */
    data object Register : RegisterScreenIntent
}