package com.hopcape.mobile.auth.presentation.components

import SocialLoginButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hopcapemobileauth.auth.generated.resources.Res
import hopcapemobileauth.auth.generated.resources.facebook_circle
import hopcapemobileauth.auth.generated.resources.google

/**
 * A composable function that represents social login providers (e.g., Google and Facebook) on the login screen.
 *
 * This function provides a row of buttons for users to log in using their social accounts. It supports two
 * primary providers: Google and Facebook. Each button includes an icon, a label, and an onClick callback
 * for handling login actions.
 *
 * #### Key Features:
 * - **Modularity**: Encapsulates the logic for displaying social login buttons, making it reusable across screens.
 * - **Customizable Labels**: Allows customization of button labels for localization or branding purposes.
 * - **Separation of Concerns**: Delegates login actions to callbacks (`onLoginWithGoogle` and `onLoginWithFacebook`),
 *   ensuring the UI remains decoupled from business logic.
 *
 * #### Example Usage:
 * ```kotlin
 * @Composable
 * fun MyApp() {
 *     SocialLoginProviders(
 *         googleLoginButtonLabel = "Sign in with Google",
 *         facebookLoginButtonLabel = "Sign in with Facebook",
 *         onLoginWithGoogle = { println("Google login clicked") },
 *         onLoginWithFacebook = { println("Facebook login clicked") }
 *     )
 * }
 * ```
 *
 * @param modifier The [Modifier] to be applied to the root layout of the social login providers.
 * @param googleLoginButtonLabel The label for the Google login button (e.g., "Sign in with Google").
 * @param facebookLoginButtonLabel The label for the Facebook login button (e.g., "Sign in with Facebook").
 * @param onLoginWithGoogle A callback function invoked when the Google login button is clicked.
 * @param onLoginWithFacebook A callback function invoked when the Facebook login button is clicked.
 */
@Composable
internal fun SocialLoginProviders(
    modifier: Modifier = Modifier,
    googleLoginButtonLabel: String,
    facebookLoginButtonLabel: String,
    onLoginWithGoogle: () -> Unit = {},
    onLoginWithFacebook: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Google Login Button
        SocialLoginButton(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.google,
            text = googleLoginButtonLabel,
            onClick = onLoginWithGoogle,
            backgroundColor = MaterialTheme.colors.surface
        )

        // Spacer to separate the buttons
        Spacer(
            modifier = Modifier.weight(0.15f)
        )

        // Facebook Login Button
        SocialLoginButton(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.facebook_circle,
            text = facebookLoginButtonLabel,
            onClick = onLoginWithFacebook,
            backgroundColor = MaterialTheme.colors.surface
        )
    }
}