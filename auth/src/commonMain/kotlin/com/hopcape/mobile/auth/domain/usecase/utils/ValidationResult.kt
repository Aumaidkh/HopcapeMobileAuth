package com.hopcape.mobile.auth.domain.usecase.utils


/**
 * Represents the result of a validation operation.
 *
 * @property isValid Indicates whether the input is valid.
 * @property errorMessage An optional error message if the input is invalid.
 */
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)