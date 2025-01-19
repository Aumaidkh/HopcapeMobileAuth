package com.hopcape.mobile.auth.api.security

/**
 * A functional interface for encrypting data securely.
 * Implementations should provide encryption logic to convert raw (decrypted) data into an encrypted format.
 *
 * ## Usage Example:
 * ```kotlin
 * class AES256Encryptor : Encryptor {
 *     override fun encrypt(data: DecryptedData): EncryptedData {
 *         // Example AES encryption logic
 *         return "encrypted_$data"
 *     }
 * }
 *
 * val encryptor: Encryptor = AES256Encryptor()
 * val encrypted = encryptor.encrypt("my_sensitive_data")
 * println(encrypted) // Output: encrypted_my_sensitive_data
 * ```
 *
 * @author Murtaza Khursheed
 */
typealias EncryptedData = String

/**
 * A functional interface that encrypts a given [DecryptedData] and returns an [EncryptedData].
 */
fun interface Encryptor {

    /**
     * Encrypts the given plain text data.
     *
     * @param data The decrypted (plain text) data that needs to be encrypted.
     * @return The encrypted representation of the input data.
     */
    fun encrypt(data: DecryptedData): EncryptedData
}
