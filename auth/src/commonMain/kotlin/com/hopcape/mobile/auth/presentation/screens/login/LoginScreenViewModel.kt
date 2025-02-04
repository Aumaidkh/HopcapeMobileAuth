package com.hopcape.mobile.auth.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.domain.usecase.login.LoginResult
import com.hopcape.mobile.auth.domain.usecase.login.LoginUseCase
import com.hopcape.mobile.auth.domain.usecase.utils.SuspendUseCase
import com.hopcape.mobile.auth.presentation.navigation.NavRoutes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A [ViewModel] responsible for managing the state and logic of the login screen.
 *
 * This class encapsulates the business logic for handling user interactions on the login screen,
 * such as email/password input, login attempts, and navigation to the registration screen. It uses
 * a [MutableStateFlow] to manage the UI state and a [Channel] to handle one-time events like navigation.
 *
 * #### Key Features:
 * - **State Management**: Uses [MutableStateFlow] to maintain and update the UI state in a reactive manner.
 * - **Event Handling**: Uses a [Channel] to emit one-time events (e.g., navigation) that are consumed by the UI layer.
 * - **Dependency Injection**: Relies on [SuspendUseCase] for performing asynchronous operations like authentication.
 * - **Separation of Concerns**: Decouples the UI logic from the business logic, making the code easier to test and maintain.
 *
 * #### Example Usage:
 * ```kotlin
 * val viewModel = LoginScreenViewModel(loginUseCase)
 *
 * // Simulate user interaction: Email change
 * viewModel.onIntent(LoginScreenIntent.EmailChange("user@example.com"))
 *
 * // Simulate login attempt
 * viewModel.onIntent(LoginScreenIntent.Login)
 *
 * // Observe state changes
 * lifecycleScope.launch {
 *     viewModel.state.collect { state ->
 *         println("Current State: $state")
 *     }
 * }
 *
 * // Observe events
 * lifecycleScope.launch {
 *     viewModel.event.collect { event ->
 *         when (event) {
 *             is LoginScreenEvent.Navigate -> println("Navigate to ${event.route}")
 *         }
 *     }
 * }
 * ```
 *
 * @param loginUseCase A [SuspendUseCase] responsible for performing the login operation.
 */
internal class LoginScreenViewModel(
    private val loginUseCase: SuspendUseCase<LoginUseCase.Params, LoginResult>
) : ViewModel() {

    /**
     * A [MutableStateFlow] that holds the current state of the login screen.
     *
     * This property is used to manage the UI state reactively. The state includes user inputs (email, password),
     * validation errors, and the current display state (e.g., idle, loading, error, success).
     *
     * @see LoginScreenState
     */
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    /**
     * A [Channel] used to emit one-time events from the [ViewModel] to the UI layer.
     *
     * This property is used for events that should only be handled once, such as navigation to another screen.
     *
     * @see LoginScreenEvent
     */
    private val _event = Channel<LoginScreenEvent>()
    val event = _event.receiveAsFlow()

    /**
     * Handles user intents from the UI layer.
     *
     * This method acts as a dispatcher for user interactions, delegating the handling of each intent to the
     * appropriate private method.
     *
     * @param intent The user intent to handle (e.g., email change, login, register).
     *
     * @see LoginScreenIntent
     */
    fun onIntent(intent: LoginScreenIntent) {
        when (intent) {
            is LoginScreenIntent.EmailChange -> handleEmailChangeIntent(intent)
            LoginScreenIntent.Login -> handleLoginIntent()
            is LoginScreenIntent.PasswordChange -> handlePasswordChangeIntent(intent)
            LoginScreenIntent.Register -> handleRegisterIntent()
        }
    }

    /**
     * Handles the intent to update the email field.
     *
     * This method updates the [LoginScreenState.email] field with the new value and clears any existing email
     * validation errors.
     *
     * @param intent The intent containing the new email value.
     *
     * @see LoginScreenIntent.EmailChange
     */
    private fun handleEmailChangeIntent(intent: LoginScreenIntent.EmailChange) {
        _state.update {
            it.copy(
                email = intent.value,
                emailError = null
            )
        }
    }

    /**
     * Handles the intent to update the password field.
     *
     * This method updates the [LoginScreenState.password] field with the new value and clears any existing password
     * validation errors.
     *
     * @param intent The intent containing the new password value.
     *
     * @see LoginScreenIntent.PasswordChange
     */
    private fun handlePasswordChangeIntent(intent: LoginScreenIntent.PasswordChange) {
        _state.update {
            it.copy(
                password = intent.value,
                passwordError = null
            )
        }
    }

    /**
     * Handles the intent to navigate to the registration screen.
     *
     * This method emits a [LoginScreenEvent.Navigate] event with the route to the registration screen.
     */
    private fun handleRegisterIntent() {
        viewModelScope.launch {
            _event.send(LoginScreenEvent.Navigate(NavRoutes.Register))
        }
    }

    /**
     * Handles the intent to perform a login attempt.
     *
     * This method invokes the [loginUseCase] with the current email and password from the state. Based on the result,
     * it updates the state or triggers side effects (e.g., navigation, error handling).
     *
     * #### Flow:
     * 1. Invokes the [loginUseCase] with the current credentials.
     * 2. Updates the state based on the result:
     *    - Success: Updates the state to indicate success and triggers the global onAuthSuccess callback.
     *    - Error: Updates the state to indicate an error.
     *    - Validation Error: Updates the state with specific validation errors for email and password.
     */
    private fun handleLoginIntent() {
        viewModelScope.launch {
            val result = loginUseCase(
                LoginUseCase.Params(
                    email = state.value.email,
                    password = state.value.password
                )
            )
            when (result) {
                is LoginResult.Error -> {
                    _state.update {
                        it.copy(
                            displayState = DisplayState.Error
                        )
                    }
                }
                is LoginResult.Success -> {
                    _state.update {
                        it.copy(
                            displayState = DisplayState.Success
                        )
                    }
                    Authenticator.config.onAuthSuccess()
                }
                is LoginResult.ValidationError -> {
                    _state.update {
                        it.copy(
                            emailError = result.emailError,
                            passwordError = result.passwordError
                        )
                    }
                }
            }
        }
    }
}