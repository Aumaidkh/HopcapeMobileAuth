package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.security.AndroidDecryptor
import com.hopcape.mobile.auth.api.security.AndroidEncryptor
import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor

/**
 * A factory class responsible for creating platform-specific dependencies for encryption and decryption
 * in the Android environment. This implementation provides the `AndroidEncryptor` and `AndroidDecryptor`
 * for use within the authentication module.
 *
 * This class extends `PlatformSpecificAuthenticationDependencyFactory` and overrides the methods
 * to provide Android-specific encryptor and decryptor implementations.
 *
 * @author Murtaza Khursheed
 *
 * Example usage:
 * ```kotlin
 * val factory = AndroidPlatformAuthenticationDependencyFactory()
 * val encryptor: Encryptor = factory.createEncryptor()
 * val decryptor: Decryptor = factory.createDecrpytor()
 *
 * val encryptedData = encryptor.encrypt(DecryptedData("Sensitive information"))
 * val decryptedData = decryptor.decrypt(EncryptedData(encryptedData))
 * ```
 *
 * @see PlatformSpecificAuthenticationDependencyFactory
 */
class AndroidPlatformAuthenticationDependencyFactory: PlatformSpecificAuthenticationDependencyFactory() {

    /**
     * Creates and returns an instance of [Encryptor] for the Android platform.
     *
     * This implementation uses the [AndroidEncryptor] class for encryption operations.
     *
     * @return An instance of [Encryptor] tailored for Android.
     */
    override fun createEncryptor(): Encryptor {
        return AndroidEncryptor()
    }

    /**
     * Creates and returns an instance of [Decryptor] for the Android platform.
     *
     * This implementation uses the [AndroidDecryptor] class for decryption operations.
     *
     * @return An instance of [Decryptor] tailored for Android.
     */
    override fun createDecrpytor(): Decryptor {
        return AndroidDecryptor()
    }
}
