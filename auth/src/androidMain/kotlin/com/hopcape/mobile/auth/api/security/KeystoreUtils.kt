package com.hopcape.mobile.auth.api.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * This file contains functions to generate and retrieve a secret key for encryption
 * using the Android Keystore system. The key is used for secure AES-GCM encryption
 * and decryption of sensitive data.
 *
 * It utilizes Android's Keystore system to generate and securely store encryption keys,
 * ensuring that the key is only accessible to the app that generated it. The AES-GCM algorithm
 * is used with a 256-bit key size to provide strong encryption.
 *
 * ## Example Usage:
 * ```kotlin
 * val secretKey: SecretKey = getSecretKey()
 * ```
 *
 * @author Murtaza Khursheed
 */

const val ALGORITHM = "AES/GCM/NoPadding"
const val KEY_ALIAS = "HopcapeAuthEncryptionKey"
const val ANDROID_KEYSTORE = "AndroidKeyStore"
const val GCM_IV_LENGTH = 12
const val TAG_SIZE = 128

/**
 * Retrieves the secret key from the Android Keystore system. If the key does not exist,
 * a new key is generated and stored securely in the Keystore.
 *
 * @return The [SecretKey] instance used for encryption and decryption.
 */
internal fun getSecretKey(): SecretKey {
    val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
    return keyStore.getKey(KEY_ALIAS, null) as? SecretKey ?: generateSecretKey()
}

/**
 * Generates a new secret key and stores it in the Android Keystore system.
 * The key is used for AES-GCM encryption and decryption with a 256-bit key size.
 *
 * @return The newly generated [SecretKey].
 */
private fun generateSecretKey(): SecretKey {
    val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
    if (!keyStore.containsAlias(KEY_ALIAS)) {
        val keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256) // Use AES-256 for strong encryption
                .build()
        )
        keyGenerator.generateKey()
    }
    return keyStore.getKey(KEY_ALIAS, null) as SecretKey
}
