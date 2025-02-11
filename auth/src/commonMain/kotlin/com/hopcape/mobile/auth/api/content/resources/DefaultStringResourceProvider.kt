package com.hopcape.mobile.auth.api.content.resources

class DefaultStringResourceProvider: StringResourceProvider {
    override val landingScreenHeading: String
        get() = "Login"
    override val loginToYourAccount: String
        get() = "Welcome Back"
    override val emailHint: String
        get() = "abc@gmail.com"
    override val passwordHint: String
        get() = "**********"
    override val forgot: String
        get() = "Reset Password"
    override val newToApp: String
        get() = "New to Kashmir Medix"
    override val register: String
        get() = "Register"
    override val login: String
        get() = "Login"
    override val orLoginWith: String
        get() = "- Or -"
    override val google: String
        get() = "Google"
    override val facebook: String
        get() = "Facebook"
    override val apple: String
        get() = "Apple"
    override val appName: String
        get() = "Hopcape Auth"

    /**
     * A hint or placeholder text for the full name input field.
     *
     * This string provides guidance to the user about what to enter in the full name field.
     * It is typically displayed as a placeholder in text input fields to improve usability
     * and clarify expectations (e.g., "Enter your full name").
     *
     * #### Example Usage:
     * ```kotlin
     * OutlinedInputField(
     *     value = state.fullName,
     *     onValueChange = { newValue -> onIntent(ProfileIntent.FullNameChange(newValue)) },
     *     placeholder = fullNameHint,
     *     keyboardType = KeyboardType.Text
     * )
     * ```
     */
    override val fullNameHint: String
        get() = "E.g John Doe"

    /**
     * Supporting text displayed below the login button.
     *
     * This string provides additional information or instructions related to the login process.
     * For example, it might include a message like "Don't have an account? Register here" to guide
     * users who are not yet registered. It is often used in conjunction with clickable text or buttons
     * to facilitate navigation to other screens (e.g., registration).
     *
     * #### Example Usage:
     * ```kotlin
     * ClickableSpanText(
     *     text = loginButtonSupportingText,
     *     clickableText = "Register",
     *     onClick = { navigateToRegistrationScreen() }
     * )
     * ```
     */
    override val loginButtonSupportingText: String
        get() = "Already have an account?"
}