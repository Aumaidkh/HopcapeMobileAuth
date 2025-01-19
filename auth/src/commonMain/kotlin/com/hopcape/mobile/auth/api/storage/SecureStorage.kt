package com.hopcape.mobile.auth.api.storage

import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor

/**
 * A secure key-value storage wrapper that encrypts data before storing it
 * and decrypts data when retrieving it.
 *
 * This class decorates an existing [KeyValueStorage] implementation to add
 * encryption and decryption capabilities, ensuring sensitive data is stored securely.
 *
 * ## Usage Example:
 * ```kotlin
 * val storage: KeyValueStorage = LocalSettingsStorage(Settings())
 * val encryptor = AES256Encryptor() // Example encryptor
 * val decryptor = AES256Decryptor() // Example decryptor
 *
 * val secureStorage = SecureStorage(storage, encryptor, decryptor)
 *
 * secureStorage.put("auth_token", "my_secure_token") // Encrypts and stores data
 * val token = secureStorage.get("auth_token") // Retrieves and decrypts data
 * println(token) // Output: my_secure_token
 *
 * secureStorage.remove("auth_token") // Removes encrypted data
 * secureStorage.clear() // Clears all stored values
 * ```
 *
 * @param storage The underlying key-value storage implementation.
 * @param encryptor The encryption mechanism used to secure data before storing.
 * @param decryptor The decryption mechanism used to retrieve original data.
 *
 * @author Murtaza Khursheed
 */
internal class SecureStorage(
    private val storage: KeyValueStorage,
    private val encryptor: Encryptor,
    private val decryptor: Decryptor
) : KeyValueStorage {

    /**
     * Encrypts and stores a key-value pair in the storage.
     *
     * @param key The unique key to identify the value.
     * @param value The plain-text string value to be encrypted and stored.
     */
    override fun put(key: String, value: String) {
        storage.put(
            key = key,
            value = encryptor.encrypt(value)
        )
    }

    /**
     * Retrieves the stored value associated with the given key.
     * Decrypts the value before returning it.
     *
     * @param key The key for which to retrieve the value.
     * @return The decrypted value, or `null` if the key does not exist.
     */
    override fun get(key: String): String? {
        return storage.get(key)?.let {
            decryptor.decrypt(it)
        }
    }

    /**
     * Removes the stored value associated with the given key.
     *
     * @param key The key whose encrypted value should be removed.
     */
    override fun remove(key: String) {
        storage.remove(key)
    }

    /**
     * Clears all encrypted key-value pairs stored in this storage.
     */
    override fun clear() {
        storage.clear()
    }
}
