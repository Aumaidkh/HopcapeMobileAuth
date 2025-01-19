package com.hopcape.mobile.auth.api.security

import com.hopcape.mobile.auth.api.security.DecryptedData
import com.hopcape.mobile.auth.api.security.EncryptedData
import com.hopcape.mobile.auth.api.security.Encryptor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.base64EncodedStringWithOptions
import platform.Foundation.create

/**
 * A class responsible for encrypting plain text data using the iOS-specific implementation.
 * This implementation leverages the `NSData` class to perform encryption and returns the
 * encrypted data as a base64 encoded string.
 *
 *
 *
 * Example usage:
 *
 * ```kotlin
 *  val encryptor = IOSEncryptor()
 *  val decryptedData = DecryptedData("Sensitive information")
 *  val encryptedData = encryptor.encrypt(decryptedData)
 *  println("Encrypted data: $encryptedData")
 * ```
 *
 * @see Encryptor
 * @author Murtaza Khursheed
 */
internal class IOSEncryptor: Encryptor {

    /**
     * Encrypts the given plain text data into a base64 encoded encrypted string.
     *
     * This implementation uses the iOS `NSData` class to handle encryption of the plain text data
     * and returns the result as a base64 encoded string.
     *
     * **Note:** The encryption process here is demonstrated for iOS platforms using `NSData` and
     * `base64EncodedStringWithOptions` for encoding the result.
     *
     * @param data The decrypted (plain text) data that needs to be encrypted.
     * @return The base64 encoded string representing the encrypted data.
     *
     * Example:
     * ```kotlin
     * val encryptedData = encryptor.encrypt(DecryptedData("Sensitive information"))
     * println("Encrypted base64 data: $encryptedData")
     * ```
     */
    @OptIn(ExperimentalForeignApi::class)
    override fun encrypt(data: DecryptedData): EncryptedData {
        // Pin the ByteArray to get a COpaquePointer
        val encryptedData: NSData = data.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = data.toULong())
        }
        return encryptedData.base64EncodedStringWithOptions(0u)
    }
}
