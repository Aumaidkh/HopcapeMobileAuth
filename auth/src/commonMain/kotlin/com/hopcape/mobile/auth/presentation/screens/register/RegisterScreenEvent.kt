package com.hopcape.mobile.auth.presentation.screens.register

/**
 * Represents events emitted by the registration screen.
 *
 * This sealed interface encapsulates all possible one-time events that can be emitted by the
 * [RegisterScreenViewModel], such as navigation requests. These events are typically consumed by
 * the UI layer to trigger side effects like navigating to another screen.
 *
 * Example usage:
 * ```kotlin
 * val event = RegisterScreenEvent.NavigateBack
 * when (event) {
 *     is RegisterScreenEvent.NavigateBack -> println("Navigating back to the previous screen.")
 *     is RegisterScreenEvent.NavigateToOtpVerification -> println("Navigating to OTP verification screen.")
 * }
 * ```
 *
 * @author Murtaza Khursheed
 */
sealed interface RegisterScreenEvent {

    /**
     * Represents the event to navigate back to the previous screen.
     *
     * This event is triggered when the user chooses to return to the previous screen, such as the
     * login screen, without completing the registration process.
     *
     * Example usage:
     * ```kotlin
     * val event = RegisterScreenEvent.NavigateBack
     * when (event) {
     *     is RegisterScreenEvent.NavigateBack -> println("Navigating back to the previous screen.")
     * }
     * ```
     */
    data object NavigateBack : RegisterScreenEvent

    /**
     * Represents the event to navigate to the OTP verification screen.
     *
     * This event is triggered after a successful registration attempt, indicating that the user
     * should proceed to the OTP verification screen to complete the authentication process.
     *
     * Example usage:
     * ```kotlin
     * val event = RegisterScreenEvent.NavigateToOtpVerification
     * when (event) {
     *     is RegisterScreenEvent.NavigateToOtpVerification -> println("Navigating to OTP verification screen.")
     * }
     * ```
     */
    data object NavigateToOtpVerification : RegisterScreenEvent
}