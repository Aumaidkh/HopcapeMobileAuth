package com.hopcape.mobile.auth.api.content.resources

/**
 * Provides string resources for authentication-related UI content.
 *
 * This interface defines a contract for providing localized or static string resources
 * used in the authentication screens. It ensures that all text displayed in the UI is
 * centralized and easily customizable, making it easier to support multiple languages
 * or themes.
 *
 * ##### Example Usage:
 * ```kotlin
 * class DefaultStringResourceProvider : StringResourceProvider {
 *     override val landingScreenHeading: String = "Welcome to Our App"
 *     override val loginToYourAccount: String = "Login to Your Account"
 *     override val emailHint: String = "Enter your email"
 *     override val passwordHint: String = "Enter your password"
 *     override val forgot: String = "Forgot Password?"
 *     override val newToApp: String = "New to our app?"
 *     override val register: String = "Register"
 *     override val login: String = "Login"
 *     override val orLoginWith: String = "Or Login With"
 *     override val google: String = "Google"
 *     override val facebook: String = "Facebook"
 *     override val apple: String = "Apple"
 *     override val appName: String = "My Awesome App"
 * }
 *
 * // Accessing string resources
 * val stringProvider = DefaultStringResourceProvider()
 * println(stringProvider.landingScreenHeading) // Output: "Welcome to Our App"
 * println(stringProvider.loginToYourAccount)   // Output: "Login to Your Account"
 * ```
 *
 * @author Murtaza Khursheed
 */
interface StringResourceProvider {

    /**
     * The heading displayed on the landing screen.
     *
     * This string is typically used as the main title or welcome message on the initial screen
     * of the authentication flow.
     *
     * Example:
     * ```kotlin
     * val landingScreenHeading = "Welcome to Our App"
     * ```
     */
    val landingScreenHeading: String

    /**
     * The prompt instructing users to log in to their account.
     *
     * This string is displayed above the login form to guide users to enter their credentials.
     *
     * Example:
     * ```kotlin
     * val loginToYourAccount = "Login to Your Account"
     * ```
     */
    val loginToYourAccount: String

    /**
     * The placeholder text for the email input field.
     *
     * This string is used as a hint or placeholder in the email input field to guide users.
     *
     * Example:
     * ```kotlin
     * val emailHint = "Enter your email"
     * ```
     */
    val emailHint: String

    /**
     * The placeholder text for the password input field.
     *
     * This string is used as a hint or placeholder in the password input field to guide users.
     *
     * Example:
     * ```kotlin
     * val passwordHint = "Enter your password"
     * ```
     */
    val passwordHint: String

    /**
     * The label for the "Forgot Password" link.
     *
     * This string is displayed as a clickable link to allow users to reset their password.
     *
     * Example:
     * ```kotlin
     * val forgot = "Forgot Password?"
     * ```
     */
    val forgot: String

    /**
     * The message indicating that the user is new to the app.
     *
     * This string is displayed to inform users who do not yet have an account about the option
     * to register.
     *
     * Example:
     * ```kotlin
     * val newToApp = "New to our app?"
     * ```
     */
    val newToApp: String

    /**
     * The label for the registration button or link.
     *
     * This string is used to guide users to the registration screen or process.
     *
     * Example:
     * ```kotlin
     * val register = "Register"
     * ```
     */
    val register: String

    /**
     * The label for the login button.
     *
     * This string is displayed on the button that submits the login form.
     *
     * Example:
     * ```kotlin
     * val login = "Login"
     * ```
     */
    val login: String

    /**
     * The label for alternative login methods.
     *
     * This string is displayed above social login options (e.g., Google, Facebook, Apple).
     *
     * Example:
     * ```kotlin
     * val orLoginWith = "Or Login With"
     * ```
     */
    val orLoginWith: String

    /**
     * The label for logging in with Google.
     *
     * This string is displayed on the button or link for Google-based authentication.
     *
     * Example:
     * ```kotlin
     * val google = "Google"
     * ```
     */
    val google: String

    /**
     * The label for logging in with Facebook.
     *
     * This string is displayed on the button or link for Facebook-based authentication.
     *
     * Example:
     * ```kotlin
     * val facebook = "Facebook"
     * ```
     */
    val facebook: String

    /**
     * The label for logging in with Apple.
     *
     * This string is displayed on the button or link for Apple-based authentication.
     *
     * Example:
     * ```kotlin
     * val apple = "Apple"
     * ```
     */
    val apple: String

    /**
     * The name of the application.
     *
     * This string is used to display the app's name in various parts of the UI, such as the
     * landing screen or dialog titles.
     *
     * Example:
     * ```kotlin
     * val appName = "My Awesome App"
     * ```
     */
    val appName: String
}