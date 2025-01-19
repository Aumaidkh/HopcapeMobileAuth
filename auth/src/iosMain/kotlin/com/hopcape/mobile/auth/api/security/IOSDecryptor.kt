package com.hopcape.mobile.auth.api.security

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create

class IOSDecryptor: Decryptor {

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

        // Convert NSData to String using NSString and UTF-8 encoding
        val nsString = decodedData.toNSString()
        return nsString.toString() // Return as Kotlin String
    }

    // Extension function to convert NSData to NSString
    @OptIn(ExperimentalForeignApi::class)
    private fun NSData.toNSString(): NSString? {
        // Ensure length is treated as ULong
        return NSString.create(bytes = this.bytes, length = this.length.toULong(), encoding = NSUTF8StringEncoding)
    }

}
