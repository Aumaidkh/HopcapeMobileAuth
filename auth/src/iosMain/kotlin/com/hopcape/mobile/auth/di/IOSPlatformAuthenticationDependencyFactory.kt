package com.hopcape.mobile.auth.di

import com.hopcape.mobile.auth.api.security.Decryptor
import com.hopcape.mobile.auth.api.security.Encryptor
import com.hopcape.mobile.auth.api.security.IOSDecryptor
import com.hopcape.mobile.auth.api.security.IOSEncryptor

/**
 * A factory class that provides platform-specific implementations of `Encryptor` and `Decryptor`
 * for iOS. It creates instances of `IOSEncryptor` and `IOSDecryptor` for encryption and decryption
 * tasks, respectively.
 *
 * This class ensures that the correct platform-specific dependencies are used on iOS devices.
 *
 * Example usage:
 * ```kotlin
 * val dependencyFactory = IOSPlatformAuthenticationDependencyFactory()
 * val encryptor = dependencyFactory.createEncryptor()
 * val decryptor = dependencyFactory.createDecrpytor()
 * val encryptedData = encryptor.encrypt(DecryptedData("some data"))
 * val decryptedData = decryptor.decrypt(encryptedData)
 * ```
 *
 * @see IOSEncryptor
 * @see IOSDecryptor
 * @author Murtaza Khursheed
 */
class IOSPlatformAuthenticationDependencyFactory: PlatformSpecificAuthenticationDependencyFactory() {

    /**
     * Creates and returns an instance of `Encryptor` for iOS, specifically `IOSEncryptor`.
     *
     * @return The platform-specific `Encryptor` instance for iOS.
     */
    override fun createEncryptor(): Encryptor {
        return IOSEncryptor()
    }

    /**
     * Creates and returns an instance of `Decryptor` for iOS, specifically `IOSDecryptor`.
     *
     * @return The platform-specific `Decryptor` instance for iOS.
     */
    override fun createDecrpytor(): Decryptor {
        return IOSDecryptor()
    }
}
