package com.hopcape.mobile.auth.presentation.screens.login

import com.hopcape.mobile.auth.presentation.screens.utils.DisplayState

/**
 * Represents the state of the login screen in the application.
 *
 * This data class encapsulates all the necessary information required to manage the UI and logic
 * of the login screen. It includes fields for user input (email and password), validation errors,
 * and the current display state of the screen (e.g., idle, loading, error, success).
 *
 * @property email The email entered by the user. Defaults to an empty string.
 * @property emailError An optional error message for invalid email input. Null if the email is valid.
 * @property password The password entered by the user. Defaults to an empty string.
 * @property passwordError An optional error message for invalid password input. Null if the password is valid.
 * @property displayState The current display state of the login screen (e.g., idle, loading, error, success).
 *
 * #### Example Usage
 * ```
 * class LoginViewModel : ViewModel() {
 *
 *     // Private mutable state flow
 *     private val _state = MutableStateFlow(LoginScreenState())
 *
 *     // Public immutable state flow exposed to the UI
 *     val state: StateFlow<LoginScreenState> = _state.asStateFlow()
 *
 *     /**
 *      * Handles the login button click event.
 *      *
 *      * Validates the user's email and password, updates the state with any validation errors,
 *      * and performs the login operation if the inputs are valid.
 *      *
 *      * @param email The email entered by the user.
 *      * @param password The password entered by the user.
 *      */
 *     fun onLoginClicked(email: String, password: String) {
 *         // Validate inputs
 *         val emailError = validateEmail(email)
 *         val passwordError = validatePassword(password)
 *
 *         if (emailError != null || passwordError != null) {
 *             // Update state with validation errors
 *             _state.value = _state.value.copy(
 *                 email = email,
 *                 password = password,
 *                 emailError = emailError,
 *                 passwordError = passwordError,
 *                 displayState = DisplayState.Error
 *             )
 *             return
 *         }
 *
 *         // Proceed with login
 *         _state.value = _state.value.copy(displayState = DisplayState.Loading)
 *         viewModelScope.launch {
 *             try {
 *                 // Simulate a login request (replace with actual API call)
 *                 val loginSuccess = performLogin(email, password)
 *                 if (loginSuccess) {
 *                     _state.value = _state.value.copy(displayState = DisplayState.Success)
 *                 } else {
 *                     _state.value = _state.value.copy(displayState = DisplayState.Error)
 *                 }
 *             } catch (e: Exception) {
 *                 _state.value = _state.value.copy(displayState = DisplayState.Error)
 *             }
 *         }
 *     }
 *
 *     /**
 *      * Validates the email input.
 *      *
 *      * @param email The email to validate.
 *      * @return An error message if the email is invalid, or null if it is valid.
 *      */
 *     private fun validateEmail(email: String): String? {
 *         return when {
 *             email.isBlank() -> "Email cannot be empty"
 *             !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
 *             else -> null
 *         }
 *     }
 *
 *     /**
 *      * Validates the password input.
 *      *
 *      * @param password The password to validate.
 *      * @return An error message if the password is invalid, or null if it is valid.
 *      */
 *     private fun validatePassword(password: String): String? {
 *         return when {
 *             password.isBlank() -> "Password cannot be empty"
 *             password.length < 6 -> "Password must be at least 6 characters"
 *             else -> null
 *         }
 *     }
 *
 *     /**
 *      * Simulates a login request.
 *      *
 *      * Replace this with your actual login API call.
 *      *
 *      * @param email The email entered by the user.
 *      * @param password The password entered by the user.
 *      * @return True if the login is successful, false otherwise.
 *      */
 *     private suspend fun performLogin(email: String, password: String): Boolean {
 *         return email == "test@example.com" && password == "password123"
 *     }
 * }
 * ```
 */
data class LoginScreenState(
    /**
     * The email entered by the user.
     *
     * This field stores the email address provided by the user during the login process.
     * It defaults to an empty string when the screen is initialized.
     */
    val email: String = "",

    /**
     * An optional error message for invalid email input.
     *
     * This field provides feedback to the user if the email they entered is invalid.
     * For example, it may indicate that the email is empty or improperly formatted.
     * If the email is valid, this field will be null.
     */
    val emailError: String? = null,

    /**
     * The password entered by the user.
     *
     * This field stores the password provided by the user during the login process.
     * It defaults to an empty string when the screen is initialized.
     */
    val password: String = "",

    /**
     * An optional error message for invalid password input.
     *
     * This field provides feedback to the user if the password they entered is invalid.
     * For example, it may indicate that the password is empty or too short.
     * If the password is valid, this field will be null.
     */
    val passwordError: String? = null,

    /**
     * The current display state of the login screen.
     *
     * This field represents the current state of the login screen, such as whether it is idle,
     * loading, showing an error, or displaying a success message. It helps manage the UI's behavior
     * and appearance based on the ongoing process.
     */
    val displayState: DisplayState = DisplayState.Idle // Default to Idle state
)

