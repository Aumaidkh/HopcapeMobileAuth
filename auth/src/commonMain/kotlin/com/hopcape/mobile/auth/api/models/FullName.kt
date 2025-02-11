package com.hopcape.mobile.auth.api.models

import kotlin.jvm.JvmInline

/**
 * A value class representing an email address.
 *
 * This class is used to encapsulate the email as a value object and enforce validation rules.
 * The validation ensures that the email follows a valid format.
 *
 * ##### Example usage:
 *
 * ```kotlin
 * val validEmail = Email("user@example.com")
 * val invalidEmail = Email("invalid-email") // Throws IllegalArgumentException
 * ```
 *
 * @property value The email string.
 * @throws IllegalArgumentException If the email format is invalid.
 *
 * @constructor Creates an [FullName] value object.
 *
 * @author Murtaza Khursheed
 */
@JvmInline
value class FullName(val value: String) {

    init {
        require(isValidName(value)) { "Invalid name format: $value" }
    }

    /**
     * Validates whether the provided email string is in a correct format.
     *
     * This simple validation checks if the email contains an '@' symbol and a '.' symbol after '@'.
     *
     * @param email The email string to validate.
     * @return True if the email is in a valid format, otherwise false.
     */
    private fun isValidName(email: String): Boolean {
        return email.isNotBlank()
    }
}
