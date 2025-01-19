package com.hopcape.mobile.auth.api.storage

/**
 * A generic key-value storage interface for persisting small amounts of data.
 * Implementations may use SharedPreferences, EncryptedStorage, DataStore, etc.
 *
 * ##### Usage Example:
 * ```kotlin
 * val storage: KeyValueStorage = SharedPreferencesStorage(context)
 * storage.put("auth_token", "my_secure_token")
 * val token = storage.get("auth_token") // Retrieves "my_secure_token"
 * storage.remove("auth_token") // Deletes the stored token
 * storage.clear() // Clears all stored data
 * ```
 *
 * @author Murtaza Khursheed
 */
interface KeyValueStorage {

    /**
     * Stores a key-value pair in the storage.
     *
     * @param key The unique key to identify the value.
     * @param value The string value to be stored.
     */
    fun put(key: String, value: String)

    /**
     * Retrieves the stored value associated with the given key.
     *
     * @param key The key for which to retrieve the value.
     * @return The stored value, or `null` if the key does not exist.
     */
    fun get(key: String): String?

    /**
     * Removes the stored value associated with the given key.
     *
     * @param key The key whose value should be removed.
     */
    fun remove(key: String)

    /**
     * Clears all key-value pairs stored in this storage.
     */
    fun clear()
}
