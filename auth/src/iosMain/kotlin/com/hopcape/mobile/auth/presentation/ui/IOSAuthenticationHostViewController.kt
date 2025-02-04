package com.hopcape.mobile.auth.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.compose.rememberNavController

/**
 * A Compose UI ViewController that hosts the authentication app within an iOS environment.
 * This ViewController is designed to be used for embedding the Compose-based `HopcapeMobileAuthApp` in a native iOS application.
 *
 * ##### Example usage:
 * ```kotlin
 * val authenticationHostViewController = ComposeUIViewController(
 *     configure = {
 *         enforceStrictPlistSanityCheck = false
 *     }
 * ) {
 *     HopcapeMobileAuthApp(
 *         modifier = Modifier.fillMaxSize()
 *     )
 * }
 * ```
 * In this example, the `authenticationHostViewController` creates a Compose-based UI for the authentication app (`HopcapeMobileAuthApp`) and configures it with specific settings, such as disabling strict plist sanity checks.
 *
 * @author Murtaza Khursheed
 */
internal val authenticationHostViewController = ComposeUIViewController(
    configure = {
        enforceStrictPlistSanityCheck = false
    }
){
    val navController = rememberNavController()
    HopcapeMobileAuthApp(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController
    )
}
