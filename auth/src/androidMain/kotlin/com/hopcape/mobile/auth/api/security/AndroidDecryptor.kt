package com.hopcape.mobile.auth.api.security

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec

/**
 * A class responsible for decrypting data that was previously encrypted using GCM (Galois/Counter Mode).
 * This implementation performs decryption by extracting the initialization vector (IV) from the
 * encrypted data and using it with the secret key to restore the original plain text data.
 *
 * @author Murtaza Khursheed
 *
 * Example usage:
 * ```kotlin
 * val decryptor = AndroidDecryptor()
 * val encryptedText = EncryptedData("Encrypted data in base64 format")
 * val decryptedText = decryptor.decrypt(encryptedText)
 * println("Decrypted data: $decryptedText")
 * ```
 *
 * @see Decryptor
 */
internal class AndroidDecryptor: Decryptor {

    /**
     * Decrypts the given encrypted data that was previously encrypted using GCM (Galois/Counter Mode).
     *
     * The method extracts the initialization vector (IV) and encrypted data from the input,
     * then uses the secret key to perform decryption and restore the original plain text data.
     *
     * **Note:** On Android versions below Oreo (API level 26), `java.util.Base64` is not available.
     * In such cases, you can use the `android.util.Base64` class instead.
     *
     * @param data The encrypted data in base64 format that needs to be decrypted.
     * @return The original plain text data as a `DecryptedData` object.
     *
     * Example:
     * ```kotlin
     * val decryptedData = decryptor.decrypt(EncryptedData("Encrypted base64 string"))
     * println("Decrypted data: $decryptedData")
     * ```
     */
    override fun decrypt(data: EncryptedData): DecryptedData {
        // Use Android's Base64 class for devices below API level 26
        val encryptedBytes = Base64.decode(data, Base64.NO_WRAP)

        // Extract IV and encrypted data
        val iv = encryptedBytes.copyOfRange(0, GCM_IV_LENGTH)
        val encryptedData = encryptedBytes.copyOfRange(GCM_IV_LENGTH, encryptedBytes.size)

        // Perform decryption
        val cipher = Cipher.getInstance(ALGORITHM).apply {
            init(Cipher.DECRYPT_MODE, getSecretKey(), GCMParameterSpec(TAG_SIZE, iv))
        }

        // Decrypt and return as DecryptedData
        val decryptedBytes = cipher.doFinal(encryptedData)
        return String(decryptedBytes)  // Convert decrypted bytes to a String
    }
}
