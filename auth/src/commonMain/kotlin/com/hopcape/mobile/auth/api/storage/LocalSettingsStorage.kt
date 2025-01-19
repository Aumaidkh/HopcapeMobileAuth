package com.hopcape.mobile.auth.api.storage

import com.russhwolf.settings.Settings

/**
 * Implementation of [KeyValueStorage] using `Settings` from `russhwolf.settings`.
 * This class provides a simple key-value storage mechanism suitable for multi-platform applications.
 *
 * ## Usage Example:
 * ```kotlin
 * val settings = Settings() // Instance of Settings (e.g., SharedPreferences on Android)
 * val storage: KeyValueStorage = LocalSettingsStorage(settings)
 *
 * storage.put("auth_token", "my_secure_token") // Store a value
 * val token = storage.get("auth_token") // Retrieve the stored value
 * println(token) // Prints: my_secure_token
 *
 * storage.remove("auth_token") // Remove the key
 * storage.clear() // Clears all stored data
 * ```
 *
 * @property settings The `Settings` instance used for storing key-value pairs.
 */
internal class LocalSettingsStorage(
    private val settings: Settings
) : KeyValueStorage {

    /**
     * Stores a key-value pair in the storage.
     *
     * @param key The unique key to identify the value.
     * @param value The string value to be stored.
     */
    override fun put(key: String, value: String) {
        settings.putString(key, value)
    }

    /**
     * Retrieves the stored value associated with the given key.
     *
     * @param key The key for which to retrieve the value.
     * @return The stored value, or an empty string if the key does not exist.
     */
    override fun get(key: String): String? {
        return settings.getStringOrNull(key)
    }

    /**
     * Removes the stored value associated with the given key.
     *
     * @param key The key whose value should be removed.
     */
    override fun remove(key: String) {
        settings.remove(key)
    }

    /**
     * Clears all key-value pairs stored in this storage.
     * **Warning:** This action is irreversible.
     */
    override fun clear() {
        settings.clear()
    }
}
