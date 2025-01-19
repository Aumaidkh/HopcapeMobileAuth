package com.hopcape.mobile.auth.api.models

import kotlin.jvm.JvmInline

/**
 * A value class representing an ID token.
 *
 * This class is used to encapsulate the ID token as a value object and enforce validation rules.
 * The validation ensures that the ID token is not empty and matches a basic structure of a JWT token.
 *
 * ###### Example usage:
 *
 * ```kotlin
 * val validToken = IDToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhdXRoLXNlcnZpY2UifQ.S9Nz4jeZXts5Hg")
 * val invalidToken = IDToken("invalid-token") // Throws IllegalArgumentException
 * ```
 *
 * @property value The ID token string.
 * @throws IllegalArgumentException If the ID token format is invalid or empty.
 *
 * @constructor Creates an [IDToken] value object.
 *
 * @author Murtaza Khursheed
 */
@JvmInline
value class IDToken(val value: String) {

    init {
        require(value.isNotEmpty()) { "ID token cannot be empty" }
        require(isValidIDToken(value)) { "Invalid ID token format: $value" }
    }

    /**
     * Validates whether the provided ID token string is in a correct format.
     *
     * A basic validation checks if the token has three parts separated by dots (like a JWT token).
     *
     * @param token The ID token string to validate.
     * @return True if the token appears to be a valid JWT structure, otherwise false.
     */
    private fun isValidIDToken(token: String): Boolean {
        return token.split('.').size == 3
    }
}
