package com.hopcape.mobile.auth.presentation.screens.register

import com.hopcape.mobile.auth.presentation.screens.utils.DisplayState

/**
 * Represents the state of the registration screen.
 *
 * This data class encapsulates all the UI-related data for the registration screen, including user input,
 * validation errors, and the current display state (e.g., idle, success, error). It is used by the
 * [RegisterScreenViewModel] to manage the screen's state and update the UI accordingly.
 *
 * Example usage:
 * ```kotlin
 * val initialState = RegisterScreenState()
 * val updatedState = initialState.copy(
 *     fullName = "John Doe",
 *     email = "john.doe@example.com",
 *     password = "SecurePassword123!",
 *     displayState = DisplayState.Success
 * )
 * println("Updated State: $updatedState")
 * ```
 * @author Murtaza Khursheed
 */
data class RegisterScreenState(
    /**
     * The full name entered by the user.
     *
     * This field stores the value of the full name input field. It is initially empty and updated
     * as the user types into the corresponding UI component.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(fullName = "John Doe")
     * println("Full Name: ${state.fullName}") // Output: John Doe
     * ```
     */
    val fullName: String = "",

    /**
     * The error message for the full name field, if any.
     *
     * This field stores the validation error message for the full name input field. If the field is valid,
     * this value will be `null`. Otherwise, it contains a descriptive error message.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(fullNameError = "Full name cannot be empty")
     * println("Full Name Error: ${state.fullNameError}") // Output: Full name cannot be empty
     * ```
     */
    val fullNameError: String? = null,

    /**
     * The email address entered by the user.
     *
     * This field stores the value of the email input field. It is initially empty and updated as the user
     * types into the corresponding UI component.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(email = "john.doe@example.com")
     * println("Email: ${state.email}") // Output: john.doe@example.com
     * ```
     */
    val email: String = "",

    /**
     * The error message for the email field, if any.
     *
     * This field stores the validation error message for the email input field. If the field is valid,
     * this value will be `null`. Otherwise, it contains a descriptive error message.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(emailError = "Invalid email format")
     * println("Email Error: ${state.emailError}") // Output: Invalid email format
     * ```
     */
    val emailError: String? = null,

    /**
     * The password entered by the user.
     *
     * This field stores the value of the password input field. It is initially empty and updated as the user
     * types into the corresponding UI component.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(password = "SecurePassword123!")
     * println("Password: ${state.password}") // Output: SecurePassword123!
     * ```
     */
    val password: String = "",

    /**
     * The error message for the password field, if any.
     *
     * This field stores the validation error message for the password input field. If the field is valid,
     * this value will be `null`. Otherwise, it contains a descriptive error message.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(passwordError = "Password must be at least 8 characters long")
     * println("Password Error: ${state.passwordError}") // Output: Password must be at least 8 characters long
     * ```
     */
    val passwordError: String? = null,

    /**
     * The current display state of the screen.
     *
     * This field represents the current state of the UI, such as whether it is idle, displaying an error,
     * or showing a success message. It is used to control the behavior and appearance of the screen.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(displayState = DisplayState.Success)
     * println("Display State: ${state.displayState}") // Output: Success
     * ```
     */
    val displayState: DisplayState = DisplayState.Idle
) {

    /**
     * Checks if the current state represents a valid form without errors.
     *
     * This function verifies that all required fields are filled and there are no validation errors.
     *
     * Example usage:
     * ```kotlin
     * val state = RegisterScreenState(
     *     fullName = "John Doe",
     *     email = "john.doe@example.com",
     *     password = "SecurePassword123!"
     * )
     * println("Is form valid? ${state.isFormValid()}") // Output: true
     * ```
     *
     * @return `true` if the form is valid (no empty fields or errors), otherwise `false`.
     */
    fun isFormValid(): Boolean {
        return fullName.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                fullNameError == null &&
                emailError == null &&
                passwordError == null
    }

    /**
     * Resets the state to its initial values.
     *
     * This function clears all user input and resets the display state to [DisplayState.Idle].
     *
     * Example usage:
     * ```kotlin
     * val currentState = RegisterScreenState(
     *     fullName = "John Doe",
     *     email = "john.doe@example.com",
     *     password = "SecurePassword123!"
     * )
     * val resetState = currentState.reset()
     * println("Reset State: $resetState") // Output: RegisterScreenState(...)
     * ```
     *
     * @return A new instance of [RegisterScreenState] with default values.
     */
    fun reset(): RegisterScreenState {
        return this.copy(
            fullName = "",
            fullNameError = null,
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            displayState = DisplayState.Idle
        )
    }
}