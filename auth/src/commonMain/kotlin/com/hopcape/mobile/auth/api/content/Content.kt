package com.hopcape.mobile.auth.api.content

/**
 * Represents the content configuration for authentication screens.
 *
 * This data class encapsulates all the textual and visual elements required to display the
 * authentication screens, such as the login screen. It centralizes the content configuration,
 * making it easy to customize or extend for different screens or themes.
 *
 * ##### Example Usage:
 * ```kotlin
 * // Using the primary constructor with custom values
 * val customContent = Content(
 *     loginScreen = LoginScreenContent(
 *         headingImage = Res.drawable.logo,
 *         heading = "Welcome to Our App",
 *         emailLabel = "Enter Email",
 *         emailPlaceholder = "user@example.com",
 *         passwordLabel = "Enter Password",
 *         passwordPlaceholder = "********",
 *         loginButtonLabel = "Sign In",
 *         registerButtonLabel = "Create Account",
 *         registerButtonSupporedText = "Don't have an account?",
 *         appName = "My Awesome App",
 *         forgotPasswordButtonLabel = "Forgot Password?",
 *         dividerText = " -OR- ",
 *         googleLoginButtonLabel = "Sign in with Google",
 *         facebookLoginButtonLabel = "Sign in with Facebook"
 *     )
 * )
 *
 * // Accessing properties
 * println(customContent.loginScreen.heading) // Output: "Welcome to Our App"
 * println(customContent.loginScreen.emailPlaceholder) // Output: "user@example.com"
 *
 * // Using a StringResourceProvider for localization
 * val stringResourceProvider = DefaultStringResourceProvider()
 * val localizedContent = Content(
 *     loginScreen = LoginScreenContent(stringResourceProvider)
 * )
 * println(localizedContent.loginScreen.heading) // Output: "Welcome to Our App" (from StringResourceProvider)
 * ```
 */
data class Content(
    /**
     * The content configuration for the login screen.
     *
     * This property encapsulates all the textual and visual elements required to display the
     * login screen, such as headings, labels, placeholders, and button texts.
     *
     * Example:
     * ```kotlin
     * val loginScreenContent = LoginScreenContent(
     *     headingImage = Res.drawable.logo,
     *     heading = "Welcome to Our App",
     *     emailLabel = "Email",
     *     emailPlaceholder = "abc@gmail.com",
     *     passwordLabel = "Password",
     *     passwordPlaceholder = "********",
     *     loginButtonLabel = "Login",
     *     registerButtonLabel = "Register",
     *     registerButtonSupporedText = "Don't have an account?",
     *     appName = "Hopcape",
     *     forgotPasswordButtonLabel = "Forgot Password?",
     *     dividerText = " -OR- ",
     *     googleLoginButtonLabel = "Login with Google",
     *     facebookLoginButtonLabel = "Login with Facebook"
     * )
     * ```
     */
    val loginScreen: LoginScreenContent
)