package com.hopcape.mobile.auth.presentation.screens.utils

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