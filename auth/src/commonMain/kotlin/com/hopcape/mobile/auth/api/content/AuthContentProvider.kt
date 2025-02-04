package com.hopcape.mobile.auth.api.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.di.AuthenticationDependencyModuleSingleton
import com.hopcape.mobile.auth.presentation.screens.utils.ViewModelFactory
import com.hopcape.mobile.auth.presentation.screens.utils.LocalViewModelFactory
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily
import com.hopcape.mobile.auth.presentation.theme.urbanistFontFamily

/**
 * A composable function that provides authentication-related content and theming to its child composables.
 *
 * This function uses [CompositionLocalProvider] to inject authentication content (e.g., strings, images)
 * and theming configurations (e.g., fonts) into the composition tree. It ensures that all child composables
 * have access to the provided content and theme settings without explicitly passing them as parameters.
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
 * @param authContent The authentication content to be provided to the composition tree.
 *                    Defaults to the content configured in [Authenticator.config].
 *                    Example:
 *                    ```kotlin
 *                    Content(
 *                        loginScreen = LoginScreenContent(DefaultStringResourceProvider())
 *                    )
 *                    ```
 * @param content The composable lambda that defines the UI content to be displayed within the
 *                provided authentication content and theme context.
 */
@Composable
internal fun AuthContentProvider(
    authContent: Content = Authenticator.config.content,
    content: @Composable () -> Unit
) {
    /**
     * Provides the authentication content and theming configuration to the composition tree.
     *
     * - [LocalContent] is used to provide the authentication content (e.g., strings, images).
     * - [LocalUrbanistFontFamily] is used to provide the custom font family for theming.
     */
    CompositionLocalProvider(
        LocalContent provides authContent,
        LocalUrbanistFontFamily provides urbanistFontFamily(),
        LocalViewModelFactory provides AuthenticationDependencyModuleSingleton.get(ViewModelFactory::class)
    ) {
        content()
    }
}