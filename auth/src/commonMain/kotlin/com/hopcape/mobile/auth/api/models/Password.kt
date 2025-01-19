package com.hopcape.mobile.auth.api.models

import kotlin.jvm.JvmInline

/**
 * A value class representing a password.
 *
 * This class is used to encapsulate the password string as a value object and enforce validation rules.
 * The validation ensures that the password is not empty and meets certain strength requirements,
 * such as a minimum length.
 *
 * ##### Example usage:
 *
 * ```kotlin
 * val validPassword = Password("StrongPassword123")
 * val invalidPassword = Password("short") // Throws IllegalArgumentException
 * ```
 *
 * @property value The password string.
 * @throws IllegalArgumentException If the password is empty or does not meet the strength requirements.
 *
 * @constructor Creates a [Password] value object.
 *
 * @author Murtaza Khursheed
 */
@JvmInline
value class Password(val value: String) {

    init {
        require(value.isNotEmpty()) { "Password cannot be empty" }
        require(isValidPassword(value)) { "Password must be at least 8 characters long" }
    }

    /**
     * Validates whether the provided password meets the minimum strength requirements.
     *
     * The password must be at least 8 characters long.
     *
     * @param password The password string to validate.
     * @return True if the password meets the strength requirements, otherwise false.
     */
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}
