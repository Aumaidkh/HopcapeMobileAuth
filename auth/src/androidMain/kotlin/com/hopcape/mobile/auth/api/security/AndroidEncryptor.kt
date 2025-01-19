package com.hopcape.mobile.auth.api.security

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import kotlin.random.Random

/**
 * A class responsible for encrypting data using the Android-specific encryption algorithms.
 * This implementation uses the GCM (Galois/Counter Mode) for secure encryption and base64 encoding
 * for the resulting encrypted data.
 *
 * @author Murtaza Khursheed
 *
 * Example usage:
 * ```kotlin
 * val encryptor = AndroidEncryptor()
 * val plainText = DecryptedData("Sensitive data to encrypt")
 * val encryptedText = encryptor.encrypt(plainText)
 * println("Encrypted data: $encryptedText")
 * ```
 *
 * @see Encryptor
 */
internal class AndroidEncryptor: Encryptor {

    /**
     * Encrypts the given plain text data.
     *
     * This method generates a new initialization vector (IV) for each encryption operation
     * to ensure the uniqueness of the encrypted data. The IV is prepended to the encrypted data
     * and then base64 encoded for easy storage or transmission.
     *
     * @param data The decrypted (plain text) data that needs to be encrypted.
     * @return The base64-encoded encrypted data, which includes the IV and the encrypted
     *         representation of the input data.
     *
     * Example:
     * ```kotlin
     * val encryptedData = encryptor.encrypt(DecryptedData("My secret data"))
     * println("Encrypted data: $encryptedData")
     * ```
     */
    override fun encrypt(data: DecryptedData): EncryptedData {
        val cipher = Cipher.getInstance(ALGORITHM)
        val iv = ByteArray(GCM_IV_LENGTH).also { Random.nextBytes(it) }
        val secretKey = getSecretKey()

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(TAG_SIZE, iv))
        val encryptedData = cipher.doFinal(data.toByteArray())

        // Combine IV and encrypted data for storage or transmission
        val combinedData = iv + encryptedData
        return Base64.encodeToString(combinedData, Base64.NO_WRAP)
    }
}
