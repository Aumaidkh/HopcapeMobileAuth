package com.hopcape.mobile.auth.api.content

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * A [CompositionLocal] that provides access to the authentication content throughout the composition tree.
 *
 * This property allows child composables to access the centralized [Content] configuration without explicitly
 * passing it as a parameter. If no [Content] is provided in the composition tree, an error is thrown to ensure
 * that developers wrap their app with [AuthContentProvider].
 *
 * ##### Example Usage:
 * ```kotlin
 * @Composable
 * fun MyApp() {
 *     AuthContentProvider {
 *         LoginScreen()
 *     }
 * }
 *
 * @Composable
 * fun LoginScreen() {
 *     val content = LocalContent.current
 *     Column {
 *         Text(text = content.loginScreen.heading) // Accessing login screen heading
 *         TextField(
 *             value = "",
 *             onValueChange = { },
 *             placeholder = { Text(content.loginScreen.emailPlaceholder) } // Accessing email placeholder
 *         )
 *         Button(onClick = { /* Handle login */ }) {
 *             Text(content.loginScreen.loginButtonLabel) // Accessing login button label
 *         }
 *     }
 * }
 * ```
 *
 * @throws IllegalStateException If no [Content] is provided in the composition tree. Ensure that your app is wrapped
 *                               with [AuthContentProvider] to avoid this error.
 */
val LocalContent = staticCompositionLocalOf<Content> {
    error("No Content provided. Wrap your app with AuthContentProvider.")
}