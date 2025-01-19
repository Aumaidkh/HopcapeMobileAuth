package com.hopcape.mobile.auth.api.security

/**
 * A functional interface for decrypting encrypted data securely.
 * Implementations should provide decryption logic to convert encrypted data back into its original form.
 *
 * ## Usage Example:
 * ```kotlin
 * class AES256Decryptor : Decryptor {
 *     override fun decrypt(data: EncryptedData): DecryptedData {
 *         // Example AES decryption logic
 *         return data.removePrefix("encrypted_")
 *     }
 * }
 *
 * val decryptor: Decryptor = AES256Decryptor()
 * val decrypted = decryptor.decrypt("encrypted_my_sensitive_data")
 * println(decrypted) // Output: my_sensitive_data
 * ```
 *
 * @author Murtaza Khursheed
 */
typealias DecryptedData = String

/**
 * A functional interface that decrypts a given [EncryptedData] and returns a [DecryptedData].
 */
fun interface Decryptor {

    /**
     * Decrypts the given encrypted data.
     *
     * @param data The encrypted data that needs to be decrypted.
     * @return The original plain text data.
     */
    fun decrypt(data: EncryptedData): DecryptedData
}
