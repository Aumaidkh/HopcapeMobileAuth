package com.hopcape.mobile.auth.api.models

import kotlin.jvm.JvmInline

/**
 * A value class representing an ID.
 * This class wraps a [String] that uniquely identifies an entity (e.g., a user, a session).
 * It is used to encapsulate ID values and provide type safety, ensuring that only valid IDs are passed around.
 *
 * ##### Example usage:
 *
 * **Creating an ID instance:**
 * ```kotlin
 * val userId = ID("12345")
 * ```
 *
 * **Accessing the value of ID:**
 * ```kotlin
 * val idValue = userId.value
 * ```
 *
 * **Using ID in a data class:**
 * ```kotlin
 * data class User(
 *     val id: ID,
 *     val username: String
 * )
 *
 * val user = User(id = ID("12345"), username = "user123")
 * ```
 *
 * @property value The actual string value of the ID.
 */
@JvmInline
value class ID(val value: String)
