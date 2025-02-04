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
}