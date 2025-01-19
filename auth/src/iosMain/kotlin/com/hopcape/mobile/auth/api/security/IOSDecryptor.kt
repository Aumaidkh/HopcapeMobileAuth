package com.hopcape.mobile.auth.api.security

import com.hopcape.mobile.auth.api.security.DecryptedData
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.EncryptedData
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create
import platform.Foundation.getBytes
import kotlin.test.todo

/**
 * A class responsible for decrypting encrypted data on the iOS platform.
 * This implementation decodes the Base64 encoded encrypted data, converts it into a `ByteArray`,
 * and returns the decrypted data in the form of a `ByteArray`.
 *
 * Example usage:
 * ```kotlin
 * val decryptor = IOSDecryptor()
 * val encryptedData = EncryptedData("EncryptedDataString")
 * val decryptedData = decryptor.decrypt(encryptedData)
 * println("Decrypted data: ${String(decryptedData)}")
 * ```
 *
 * @see Decryptor
 * @author Murtaza Khursheed
 */
internal class IOSDecryptor: Decryptor {

    /**
     * Decrypts the given encrypted data and returns the original plain text data.
     * The encrypted data is expected to be a Base64 encoded string.
     * This implementation decodes the Base64 string, converts the resulting data into
     * a `ByteArray`, and returns it.
     *
     * @param data The encrypted data (Base64 encoded string) that needs to be decrypted.
     * @return A `ByteArray` representing the decrypted (plain text) data.
     *
     * Example:
     * ```kotlin
     * val encryptedData = EncryptedData("EncryptedBase64String")
     * val decryptedData = decryptor.decrypt(encryptedData)
     * println("Decrypted byte array: $decryptedData")
     * ```
     */
    @OptIn(ExperimentalForeignApi::class)
    override fun decrypt(data: EncryptedData): DecryptedData {
        // Decode the Base64 encoded string to NSData
        val decodedData = NSData.create(base64EncodedString = data, options = 0u)
            ?: throw IllegalArgumentException("Invalid Base64 string")

        // Convert NSData back to ByteArray
        val byteArray = ByteArray(decodedData.length.toInt())
        byteArray.usePinned { pinned ->
            decodedData.getBytes(pinned.addressOf(0), decodedData.length)
        }
        return byteArray.toString()
    }
}
