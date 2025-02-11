package com.hopcape.mobile.auth.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.domain.usecase.register.RegisterResult
import com.hopcape.mobile.auth.domain.usecase.register.RegisterUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.presentation.screens.utils.DisplayState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the registration screen's state and user interactions.
 *
 * This class handles user input (email, password, full name), validates the input, and triggers
 * the registration process via the [RegisterUseCase]. It also manages UI state updates and events
 * such as navigation and error handling.
 *
 * Example usage:
 * ```kotlin
 * val viewModel = RegisterScreenViewModel(registerUseCase)
 * viewModel.onIntent(RegisterScreenIntent.EmailChange("john.doe@example.com"))
 * viewModel.onIntent(RegisterScreenIntent.PasswordChange("SecurePassword123!"))
 * viewModel.onIntent(RegisterScreenIntent.FullNameChange("John Doe"))
 * viewModel.onIntent(RegisterScreenIntent.Register)
 * ```
 *
 * @param useCase The [RegisterUseCase] instance used to perform the registration logic.
 * @author Murtaza Khursheed
 */
internal class RegisterScreenViewModel(
    private val useCase: SuspendUseCase<RegisterUseCase.Params, RegisterResult>
) : ViewModel() {

    /**
     * Represents the mutable state of the registration screen.
     */
    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    /**
     * Represents the event channel for emitting one-time events like navigation.
     */
    private val _event = Channel<RegisterScreenEvent>()
    val event = _event.receiveAsFlow()

    /**
     * Handles user intents from the UI layer.
     *
     * This function processes various user actions such as input changes, registration attempts,
     * and navigation requests. It updates the UI state or emits events accordingly.
     *
     * Example usage:
     * ```kotlin
     * viewModel.onIntent(RegisterScreenIntent.EmailChange("new.email@example.com"))
     * viewModel.onIntent(RegisterScreenIntent.Register)
     * ```
     *
     * @param intent The user intent to handle.
     */
    fun onIntent(intent: RegisterScreenIntent) {
        when (intent) {
            is RegisterScreenIntent.EmailChange -> updateEmail(intent.value)
            is RegisterScreenIntent.PasswordChange -> updatePassword(intent.value)
            is RegisterScreenIntent.FullNameChange -> updateFullName(intent.value)
            is RegisterScreenIntent.Register -> registerUser()
            is RegisterScreenIntent.Login -> navigateToLogin()
        }
    }

    /**
     * Updates the email field in the UI state.
     *
     * @param email The new email value entered by the user.
     */
    private fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    /**
     * Updates the password field in the UI state.
     *
     * @param password The new password value entered by the user.
     */
    private fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    /**
     * Updates the full name field in the UI state.
     *
     * @param fullName The new full name value entered by the user.
     */
    private fun updateFullName(fullName: String) {
        _state.update { it.copy(fullName = fullName) }
    }

    /**
     * Initiates the registration process using the [RegisterUseCase].
     *
     * This function validates the user input and delegates the registration request to the
     * [RegisterUseCase]. It updates the UI state based on the result (success, validation error, or failure).
     */
    private fun registerUser() {
        viewModelScope.launch {
            val currentState = state.value
            val result = useCase(
                RegisterUseCase.Params(
                    email = currentState.email,
                    password = currentState.password,
                    fullName = currentState.fullName
                )
            )

            when (result) {
                is RegisterResult.Error -> handleRegistrationError(result.errorMessage)
                is RegisterResult.Success -> handleRegistrationSuccess()
                is RegisterResult.ValidationError -> handleValidationError(result)
            }
        }
    }

    /**
     * Handles a registration error by updating the UI state.
     *
     * @param errorMessage The error message describing the failure.
     */
    private fun handleRegistrationError(errorMessage: String) {
        _state.update { it.copy(displayState = DisplayState.Error) }
    }

    /**
     * Handles a successful registration by updating the UI state and triggering post-authentication logic.
     */
    private fun handleRegistrationSuccess() {
        _state.update { it.copy(displayState = DisplayState.Success) }
        Authenticator.config.onAuthSuccess()
    }

    /**
     * Handles validation errors by updating the UI state with error messages.
     *
     * @param validationError The validation error containing details about invalid fields.
     */
    private fun handleValidationError(validationError: RegisterResult.ValidationError) {
        _state.update {
            it.copy(
                emailError = validationError.emailError,
                passwordError = validationError.passwordError,
                fullNameError = validationError.fullNameError
            )
        }
    }

    /**
     * Navigates the user back to the login screen.
     */
    private fun navigateToLogin() {
        viewModelScope.launch {
            _event.send(RegisterScreenEvent.NavigateBack)
        }
    }
}