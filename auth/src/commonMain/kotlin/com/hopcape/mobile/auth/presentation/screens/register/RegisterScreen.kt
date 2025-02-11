package com.hopcape.mobile.auth.presentation.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.hopcape.mobile.auth.api.content.RegisterScreenContent
import com.hopcape.mobile.auth.presentation.components.OutlinedInputField
import com.hopcape.mobile.auth.presentation.components.SocialLoginProviders
import com.hopcape.mobile.auth.presentation.screens.utils.DisplayState
import com.hopcape.mobile.auth.presentation.screens.utils.emailFieldTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.fullNameFieldTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.headingImageTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.loginButtonTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.passwordFieldTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.registerButtonTestTag
import com.hopcape.mobile.auth.presentation.screens.utils.registerScreenTestTag
import com.hopcape.mobile.auth.presentation.theme.LocalUrbanistFontFamily
import hopcapemobileauth.auth.generated.resources.Res
import hopcapemobileauth.auth.generated.resources.at
import hopcapemobileauth.auth.generated.resources.ic_eye
import hopcapemobileauth.auth.generated.resources.ic_eye_slash
import hopcapemobileauth.auth.generated.resources.ic_person
import hopcapemobileauth.auth.generated.resources.key
import hopcapemobileauth.auth.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

/**
 * Represents the registration screen UI.
 *
 * This composable function displays the registration form, including fields for full name, email,
 * and password, along with buttons for registration, login, and social logins. It also handles user
 * interactions via the [onIntent] callback and reflects the current state of the screen using the
 * [state] parameter.
 *
 * Example usage:
 * ```kotlin
 * RegisterScreen(
 *     content = RegisterScreenContent(...),
 *     onIntent = { intent -> handleIntent(intent) },
 *     state = viewModel.state.value
 * )
 * ```
 *
 * @param modifier The modifier to be applied to the root layout.
 * @param content The content (strings, labels, placeholders) for the registration screen.
 * @param onIntent A callback function to handle user intents (e.g., input changes, button clicks).
 * @param state The current state of the registration screen, including user input and errors.
 *
 * @author Murtaza Khursheed
 */
@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    content: RegisterScreenContent,
    onIntent: (RegisterScreenIntent) -> Unit,
    state: RegisterScreenState,
) {
    val fontFamily = LocalUrbanistFontFamily.current
    val scrollState = rememberScrollState()
    var showPassword by remember { mutableStateOf(false) }

    /**
     * Root Column for the registration screen.
     *
     * This column contains all the UI components of the registration screen, such as the app logo,
     * input fields, buttons, and social login options. It is scrollable to accommodate smaller screens.
     */
    Column(
        modifier = modifier
            .testTag(registerScreenTestTag)
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(vertical = 24.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(content) {
            // App Logo
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

            // Full Name Input Field
            OutlinedInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(fullNameFieldTestTag),
                onValueChange = { onIntent(RegisterScreenIntent.FullNameChange(it)) },
                keyboardType = KeyboardType.Text,
                value = state.fullName,
                placeholder = fullNamePlaceholder,
                startIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.ic_person),
                        contentDescription = "Full Name Icon"
                    )
                },
                errorMessage = state.fullNameError,
                isError = state.fullNameError != null
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Email Input Field
            OutlinedInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(emailFieldTestTag),
                onValueChange = { onIntent(RegisterScreenIntent.EmailChange(it)) },
                keyboardType = KeyboardType.Email,
                value = state.email,
                placeholder = emailPlaceholder,
                startIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
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
                isPassword = !showPassword,
                keyboardType = KeyboardType.Password,
                onValueChange = { onIntent(RegisterScreenIntent.PasswordChange(it)) },
                value = state.password,
                placeholder = passwordPlaceholder,
                startIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.key),
                        contentDescription = "Password Icon"
                    )
                },
                endIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { showPassword = !showPassword },
                        painter = painterResource(if (showPassword) Res.drawable.ic_eye_slash else Res.drawable.ic_eye),
                        contentDescription = "Toggle Password Visibility"
                    )
                },
                errorMessage = state.passwordError,
                isError = state.passwordError != null
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Register Button
            ProgressButton(
                modifier = Modifier
                    .testTag(registerButtonTestTag)
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                onClick = { onIntent(RegisterScreenIntent.Register) },
                text = registerButtonLabel,
                isLoading = state.displayState is DisplayState.Loading
            )

            // Login Button
            ClickableSpanText(
                modifier = Modifier
                    .testTag(loginButtonTestTag),
                text = loginButtonSupportedText,
                clickableText = loginButtonLabel,
                onClick = { onIntent(RegisterScreenIntent.Login) },
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

            // Social Login Providers
            SocialLoginProviders(
                googleLoginButtonLabel = googleLoginButtonLabel,
                facebookLoginButtonLabel = facebookLoginButtonLabel,
                onLoginWithGoogle = {},
                onLoginWithFacebook = {}
            )
        }
    }
}