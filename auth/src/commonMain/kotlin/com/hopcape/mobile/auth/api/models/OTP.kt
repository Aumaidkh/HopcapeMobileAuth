package com.hopcape.mobile.auth.api.models

import kotlin.jvm.JvmInline

/**
 * A value class representing a One-Time Password (OTP).
 *
 * This class wraps a string value to ensure type safety when handling OTPs. It prevents accidental misuse
 * of OTPs as regular strings and provides a clear semantic meaning to the data.
 *
 * ### Key Features:
 * - Ensures type safety by wrapping the OTP string.
 * - Provides utility methods for validation and formatting.
 * - Lightweight due to Kotlin's value class optimization (no runtime overhead).
 *
 * @property value The actual OTP string wrapped by this class.
 *
 * @throws IllegalArgumentException If the provided OTP string is invalid or does not meet the required format.
 *
 * ### Example Usage:
 * ```kotlin
 * val otp = OTP("123456")
 * println("OTP: ${otp.value}") // Output: OTP: 123456
 *
 * if (otp.isValid()) {
 *     println("OTP is valid!")
 * } else {
 *     println("OTP is invalid!")
 * }
 * ```
 */
@JvmInline
value class OTP private constructor(val value: String) {

    companion object {
        /**
         * Creates an [OTP] instance after validating the input.
         *
         * This method ensures that the provided OTP string meets the required format (e.g., numeric and fixed length).
         * If the input is invalid, an exception is thrown.
         *
         * @param rawOtp The raw OTP string to be validated and wrapped.
         * @return A valid [OTP] instance if the input passes validation.
         *
         * @throws IllegalArgumentException If the OTP is null, empty, non-numeric, or does not meet the required length.
         *
         * ### Example Usage:
         * ```kotlin
         * val otp = OTP.create("123456")
         * println("Created OTP: ${otp.value}") // Output: Created OTP: 123456
         * ```
         */
        fun create(rawOtp: String): OTP {
            require(rawOtp.isNotEmpty()) { "OTP cannot be empty." }
            require(rawOtp.matches(Regex("\\d{6}"))) { "OTP must be a 6-digit numeric string." }
            return OTP(rawOtp)
        }
    }

    /**
     * Validates whether the OTP meets the required format.
     *
     * This method checks if the OTP is a 6-digit numeric string. It can be used to validate user input before
     * creating an [OTP] instance.
     *
     * @return `true` if the OTP is valid, `false` otherwise.
     *
     * ### Example Usage:
     * ```kotlin
     * val rawOtp = "123456"
     * if (OTP.isValid(rawOtp)) {
     *     println("OTP is valid!")
     * } else {
     *     println("OTP is invalid!")
     * }
     * ```
     */
    fun isValid(rawOtp: String): Boolean {
        return rawOtp.matches(Regex("\\d{6}"))
    }

    /**
     * Converts the OTP to a masked format for secure display.
     *
     * This method replaces all characters in the OTP with asterisks (`*`) except the last two digits,
     * which are displayed as-is. It is useful for displaying OTPs in logs or UIs without exposing the full value.
     *
     * @return A masked version of the OTP.
     *
     * ### Example Usage:
     * ```kotlin
     * val otp = OTP.create("123456")
     * println("Masked OTP: ${otp.toMasked()}") // Output: Masked OTP: ****56
     * ```
     */
    fun toMasked(): String {
        return "*".repeat(value.length - 2) + value.takeLast(2)
    }

    /**
     * Overrides the `toString` method to provide a safe representation of the OTP.
     *
     * This method returns a masked version of the OTP instead of the raw value to prevent accidental exposure.
     *
     * @return A masked version of the OTP.
     */
    override fun toString(): String = toMasked()
}