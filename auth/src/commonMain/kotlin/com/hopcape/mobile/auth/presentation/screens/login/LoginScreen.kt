package com.hopcape.mobile.auth.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.m.auth.presentation.components.ClickableSpanText
import com.hopcape.m.auth.presentation.components.ProgressButton
import com.hopcape.mobile.auth.api.content.LocalContent
import com.hopcape.mobile.auth.api.content.LoginScreenContent
import com.hopcape.mobile.auth.presentation.components.OutlinedInputField
import com.hopcape.mobile.auth.presentation.components.SocialLoginProviders
import com.hopcape.mobile.auth.presentation.screens.utils.DisplayState
import com.hopcape.mobile.auth.presentation.screens.utils.emailFieldTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.forgotPasswordTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.headingImageTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.landingScreenTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.loginButtonTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.passwordFieldTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.registerButtonTestTag
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily
import hopcapemobileauth.auth.generated.resources.Res
import hopcapemobileauth.auth.generated.resources.at
import hopcapemobileauth.auth.generated.resources.ic_eye
import hopcapemobileauth.auth.generated.resources.ic_eye_slash
import hopcapemobileauth.auth.generated.resources.key
import hopcapemobileauth.auth.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

/**
 * A composable function that represents the login screen of the application.
 *
 * This function defines the UI layout and behavior of the login screen, including input fields for email
 * and password, social login buttons, and navigation options (e.g., registration). It uses a reactive state
 * ([LoginScreenState]) to manage user inputs and display validation errors or loading states.
 *
 * #### Key Features:
 * - **Responsive Layout**: The screen is scrollable and adapts to different screen sizes.
 * - **Validation Feedback**: Displays error messages for invalid email or password inputs.
 * - **Social Login Support**: Provides buttons for Google and Facebook login.
 * - **Navigation**: Includes options to navigate to the registration screen or reset the password.
 *
 * #### Example Usage:
 * ```kotlin
 * @Composable
 * fun MyApp(viewModel: LoginScreenViewModel) {
 *     val state by viewModel.state.collectAsState()
 *     LoginScreen(
 *         modifier = Modifier.fillMaxSize(),
 *         onIntent = viewModel::onIntent,
 *         state = state
 *     )
 * }
 * ```
 *
 * @param modifier The [Modifier] to be applied to the root layout of the login screen.
 * @param content The content configuration for the login screen, such as labels, placeholders, and button texts.
 *                Defaults to the content provided by [LocalContent].
 * @param onIntent A callback function to handle user intents (e.g., email change, login, register).
 * @param state The current state of the login screen, including user inputs, validation errors, and display state.
 */
@Composable
internal fun LoginScreen(
    modifier: Modifier,
    content: LoginScreenContent = LocalContent.current.loginScreen,
    onIntent: (LoginScreenIntent) -> Unit,
    state: LoginScreenState = LoginScreenState()
) {
    var showPassword by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val fontFamily = LocalUrbanistFontFamily.current
    with(content) {
        Column(
            modifier = modifier
                .testTag(landingScreenTestTag)
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(vertical = 24.dp, horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading Image
            Card(
                modifier = Modifier
                    .padding(top = 90.dp, bottom = 0.dp)
            ) {
                Image(
                    modifier = Modifier
                        .testTag(headingImageTestTag)
                        .size(140.dp),
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = "App Logo"
                )
            }

            // App Name
            Text(
                text = appName,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(vertical = 24.dp)
            )

            // Email Input Field
            OutlinedInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(emailFieldTestTag),
                onValueChange = { onIntent(LoginScreenIntent.EmailChange(it)) },
                keyboardType = KeyboardType.Email,
                value = state.email,
                placeholder = emailPlaceholder,
                startIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(Res.drawable.at),
                        contentDescription = "Email Icon"
                    )
                },
                errorMessage = state.emailError,
                isError = state.emailError != null
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Password Input Field
            OutlinedInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(passwordFieldTestTag),
                keyboardType = KeyboardType.Password,
                onValueChange = { onIntent(LoginScreenIntent.PasswordChange(it)) },
                value = state.password,
                placeholder = passwordPlaceholder,
                startIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(Res.drawable.key),
                        contentDescription = "Password Icon"
                    )
                },
                endIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { showPassword = !showPassword }
                        ,
                        painter = painterResource(if (showPassword) Res.drawable.ic_eye_slash else Res.drawable.ic_eye),
                        contentDescription = "Password Icon"
                    )
                },
                errorMessage = state.passwordError,
                isError = state.passwordError != null,
                isPassword = !showPassword
            )

            // Forgot Password Button
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(
                    onClick = {}
                ) {
                    Text(
                        modifier = Modifier
                            .testTag(forgotPasswordTestTag),
                        fontFamily = fontFamily,
                        text = forgotPasswordButtonLabel,
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            // Login Button
            ProgressButton(
                modifier = Modifier
                    .testTag(loginButtonTestTag)
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                onClick = { onIntent(LoginScreenIntent.Login) },
                text = loginButtonLabel,
                isLoading = state.displayState is DisplayState.Loading
            )

            // Register Button
            ClickableSpanText(
                modifier = Modifier
                    .testTag(registerButtonTestTag),
                text = registerButtonSupporedText,
                clickableText = registerButtonLabel,
                onClick = { onIntent(LoginScreenIntent.Register) },
                verticalPadding = 2.dp
            )

            // Divider Text
            Text(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth(),
                text = dividerText,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                textAlign = TextAlign.Center
            )

            // Social Login Buttons
            SocialLoginProviders(
                googleLoginButtonLabel = googleLoginButtonLabel,
                facebookLoginButtonLabel = facebookLoginButtonLabel,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}