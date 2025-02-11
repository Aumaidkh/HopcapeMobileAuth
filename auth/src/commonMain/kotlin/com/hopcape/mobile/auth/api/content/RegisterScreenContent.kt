package com.hopcape.mobile.auth.api.content

import com.hopcape.mobile.auth.api.content.resources.StringResourceProvider
import hopcapemobileauth.auth.generated.resources.Res
import hopcapemobileauth.auth.generated.resources.logo
import org.jetbrains.compose.resources.DrawableResource

/**
 * Represents the content configuration for the login screen.
 *
 * This data class encapsulates all the textual and visual elements required to display the login screen.
 * It provides default values for most properties, making it easy to customize or use as-is. Additionally,
 * it supports dynamic string resource injection via the [StringResourceProvider] interface, enabling
 * localization and centralized string management.
 *
 * ##### Example Usage:
 * ```kotlin
 * // Using the primary constructor with custom values
 * val customRegisterScreenContent = RegisterScreenContent(
 *     headingImage = Res.drawable.logo,
 *     heading = "Welcome to Our App",
 *     fullNameLabel = "Full Name",
 *     fullNamePlaceholder = "John Doe,
 *     emailLabel = "Enter Email",
 *     emailPlaceholder = "user@example.com",
 *     passwordLabel = "Enter Password",
 *     passwordPlaceholder = "********",
 *     loginButtonLabel = "Sign In",
 *     registerButtonLabel = "Create Account",
 *     registerButtonSupporedText = "Don't have an account?",
 *     appName = "My Awesome App",
 *     forgotPasswordButtonLabel = "Forgot Password?",
 *     dividerText = " -OR- ",
 *     googleLoginButtonLabel = "Sign in with Google",
 *     facebookLoginButtonLabel = "Sign in with Facebook"
 * )
 *
 * // Accessing properties
 * println(customRegisterScreenContent.heading) // Output: "Welcome to Our App"
 * println(customRegisterScreenContent.emailPlaceholder) // Output: "user@example.com"
 *
 * // Using the secondary constructor with StringResourceProvider
 * val stringResourceProvider = DefaultStringResourceProvider()
 * val localizedRegisterScreenContent = RegisterScreenContent(stringResourceProvider)
 * println(localizedRegisterScreenContent.heading) // Output: "Welcome to Our App" (from StringResourceProvider)
 * ```
 */
data class RegisterScreenContent(
    /**
     * The image displayed as the heading or logo on the login screen.
     *
     * This property accepts a drawable resource that represents the app's logo or branding.
     *
     * Example:
     * ```kotlin
     * val headingImage = Res.drawable.logo
     * ```
     */
    val headingImage: DrawableResource,

    /**
     * The main heading displayed on the login screen.
     *
     * This string is typically used as the title or welcome message at the top of the login screen.
     *
     * Example:
     * ```kotlin
     * val heading = "Hopcape Auth"
     * ```
     */
    val heading: String = "Hopcape Auth",

    /**
     * The label for the full name input field.
     *
     * This string is displayed above or beside the email input field to guide users.
     *
     * Example:
     * ```kotlin
     * val fullNameLabel = "Full Name"
     * ```
     */
    val fullNameLabel: String = "Full Name",

    /**
     * The placeholder text for the full name input field.
     *
     * This string is used as a hint or placeholder in the full name input field to guide users.
     *
     * Example:
     * ```kotlin
     * val fullNamePlaceholder = "E.g John Doe"
     * ```
     */
    val fullNamePlaceholder: String = "E.g John Doe",

    /**
     * The label for the email input field.
     *
     * This string is displayed above or beside the email input field to guide users.
     *
     * Example:
     * ```kotlin
     * val emailLabel = "Email"
     * ```
     */
    val emailLabel: String = "Email",

    /**
     * The placeholder text for the email input field.
     *
     * This string is used as a hint or placeholder in the email input field to guide users.
     *
     * Example:
     * ```kotlin
     * val emailPlaceholder = "abc@gmail.com"
     * ```
     */
    val emailPlaceholder: String = "abc@gmail.com",

    /**
     * The label for the password input field.
     *
     * This string is displayed above or beside the password input field to guide users.
     *
     * Example:
     * ```kotlin
     * val passwordLabel = "Password"
     * ```
     */
    val passwordLabel: String = "Password",

    /**
     * The placeholder text for the password input field.
     *
     * This string is used as a hint or placeholder in the password input field to guide users.
     *
     * Example:
     * ```kotlin
     * val passwordPlaceholder = "********"
     * ```
     */
    val passwordPlaceholder: String = "********",

    /**
     * The label for the login button.
     *
     * This string is displayed on the button that submits the login form.
     *
     * Example:
     * ```kotlin
     * val loginButtonLabel = "Login"
     * ```
     */
    val loginButtonLabel: String = "Login",

    /**
     * The label for the registration button.
     *
     * This string is displayed on the button that navigates users to the registration screen.
     *
     * Example:
     * ```kotlin
     * val registerButtonLabel = "Register"
     * ```
     */
    val registerButtonLabel: String = "Register",

    /**
     * The supporting text for the registration button.
     *
     * This string is displayed alongside the registration button to inform users about the option
     * to create a new account.
     *
     * Example:
     * ```kotlin
     * val loginButtonSupportedText = "Already have an account?"
     * ```
     */
    val loginButtonSupportedText: String = "Already have an account?",

    /**
     * The name of the application.
     *
     * This string is used to display the app's name in various parts of the UI, such as the
     * landing screen or dialog titles.
     *
     * Example:
     * ```kotlin
     * val appName = "Hopcape"
     * ```
     */
    val appName: String = "Hopcape",


    /**
     * The text displayed between alternative login methods.
     *
     * This string is used as a divider between traditional login options and social login buttons.
     *
     * Example:
     * ```kotlin
     * val dividerText = " -OR- "
     * ```
     */
    val dividerText: String = " -OR- ",

    /**
     * The label for the Google login button.
     *
     * This string is displayed on the button that initiates Google-based authentication.
     *
     * Example:
     * ```kotlin
     * val googleLoginButtonLabel = "Login with Google"
     * ```
     */
    val googleLoginButtonLabel: String = "Login with Google",

    /**
     * The label for the Facebook login button.
     *
     * This string is displayed on the button that initiates Facebook-based authentication.
     *
     * Example:
     * ```kotlin
     * val facebookLoginButtonLabel = "Login with Facebook"
     * ```
     */
    val facebookLoginButtonLabel: String = "Login with Facebook"
) {
    /**
     * Secondary constructor that initializes the login screen content using a [StringResourceProvider].
     *
     * This constructor allows for dynamic injection of localized strings, making it easier to support
     * multiple languages or themes.
     *
     * Example:
     * ```kotlin
     * val stringResourceProvider = DefaultStringResourceProvider()
     * val localizedLoginScreenContent = LoginScreenContent(stringResourceProvider)
     * println(localizedLoginScreenContent.heading) // Output: "Welcome to Our App" (from StringResourceProvider)
     * ```
     *
     * @param stringResourceProvider An implementation of [StringResourceProvider] that provides localized strings.
     */
    constructor(stringResourceProvider: StringResourceProvider) : this(
        headingImage = Res.drawable.logo,
        heading = stringResourceProvider.landingScreenHeading,
        emailLabel = stringResourceProvider.emailHint,
        emailPlaceholder = stringResourceProvider.emailHint,
        passwordLabel = stringResourceProvider.passwordHint,
        passwordPlaceholder = stringResourceProvider.passwordHint,
        fullNameLabel = stringResourceProvider.fullNameHint,
        fullNamePlaceholder = stringResourceProvider.fullNameHint,
        loginButtonLabel = stringResourceProvider.login,
        registerButtonLabel = stringResourceProvider.register,
        loginButtonSupportedText = stringResourceProvider.loginButtonSupportingText,
        appName = stringResourceProvider.appName,
        dividerText = stringResourceProvider.orLoginWith,
        googleLoginButtonLabel = stringResourceProvider.google,
        facebookLoginButtonLabel = stringResourceProvider.facebook,
    )
}