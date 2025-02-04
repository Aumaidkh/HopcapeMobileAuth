package com.hopcape.mobile.auth.presentation.screens.login

import com.hopcape.mobile.auth.presentation.navigation.NavRoutes

/**
 * Represents the possible events that can occur on the login screen.
 *
 * This sealed interface defines a restricted hierarchy of events that the login screen can handle.
 * Each event corresponds to a specific user interaction or system action, such as navigating to
 * another screen.
 *
 * #### Example Usage
 * ```
 * import androidx.lifecycle.ViewModel
 * import androidx.lifecycle.viewModelScope
 * import kotlinx.coroutines.channels.Channel
 * import kotlinx.coroutines.flow.receiveAsFlow
 * import kotlinx.coroutines.launch
 *
 * class LoginViewModel : ViewModel() {
 *
 *     // Channel to send events to the UI layer
 *     private val _event = Channel<LoginScreenEvent>(Channel.BUFFERED)
 *     val event = _event.receiveAsFlow()
 *
 *     /**
 *      * Handles a navigation request to a specific route.
 *      *
 *      * This function triggers a navigation event by sending a [LoginScreenEvent.Navigate] event
 *      * to the UI layer.
 *      *
 *      * @param route The destination route to navigate to.
 *      */
 *     fun navigateTo(route: NavRoutes) {
 *         viewModelScope.launch {
 *             _event.send(LoginScreenEvent.Navigate(route))
 *         }
 *     }
 * }
 * ```
 */
sealed interface LoginScreenEvent {

    /**
     * Represents a navigation event from the login screen.
     *
     * This event is triggered when the user needs to navigate to a different screen in the
     * application. It encapsulates the destination route for navigation.
     *
     * @property route The destination route to navigate to. This is typically an enum or sealed
     *                 class representing the app's navigation routes.
     */
    data class Navigate(val route: NavRoutes) : LoginScreenEvent
}