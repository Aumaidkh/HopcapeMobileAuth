package com.hopcape.mobile.auth.presentation.screens.login

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

/**
 * Represents the possible display states of the login screen.
 *
 * This sealed interface defines a restricted hierarchy of states that the login screen can be in.
 * Each state corresponds to a specific scenario during the login process, such as being idle,
 * loading, encountering an error, or successfully completing the login.
 */
sealed interface DisplayState {

    /**
     * Indicates that the login screen is in an idle state.
     *
     * This state is used when the screen is initialized but no action has been taken yet.
     * It typically represents the default state before the user interacts with the screen.
     */
    data object Idle : DisplayState

    /**
     * Indicates that the login process is in progress.
     *
     * This state is typically used to show a loading indicator while the application is
     * communicating with the server or performing other asynchronous operations.
     */
    data object Loading : DisplayState

    /**
     * Indicates that an error has occurred during the login process.
     *
     * This state is used to inform the user that something went wrong, such as invalid credentials
     * or a network failure. Additional error details can be provided via the UI layer.
     */
    data object Error : DisplayState

    /**
     * Indicates that the login process was successful.
     *
     * This state is used to notify the user that they have successfully logged in. The UI layer
     * can transition to the next screen or display a success message.
     */
    data object Success : DisplayState
}