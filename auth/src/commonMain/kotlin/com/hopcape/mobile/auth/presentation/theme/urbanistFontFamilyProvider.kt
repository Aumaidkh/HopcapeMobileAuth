package com.hopcape.mobile.auth.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily

/**
 * A [CompositionLocal] that provides access to the Urbanist font family throughout the composition tree.
 *
 * This property allows child composables to access the centralized [FontFamily] configuration for the Urbanist font
 * without explicitly passing it as a parameter. If no Urbanist font family is provided in the composition tree,
 * an error is thrown to ensure developers properly configure the font family before using it.
 *
 * ##### Example Usage:
 * ```kotlin
 * @Composable
 * fun MyApp() {
 *     val urbanistFont = FontFamily(
 *         // Define or load the Urbanist font here
 *     )
 *     CompositionLocalProvider(LocalUrbanistFontFamily provides urbanistFont) {
 *         LoginScreen()
 *     }
 * }
 *
 * @Composable
 * fun LoginScreen() {
 *     val urbanistFont = LocalUrbanistFontFamily.current
 *     Text(
 *         text = "Welcome to Our App",
 *         fontFamily = urbanistFont // Using the Urbanist font family
 *     )
 * }
 * ```
 *
 * @throws IllegalStateException If no Urbanist font family is provided in the composition tree. Ensure that your app
 *                               wraps the relevant composables with a `CompositionLocalProvider` for `LocalUrbanistFontFamily`.
 */
val LocalUrbanistFontFamily = staticCompositionLocalOf<FontFamily> {
    error("Urbanist Font family not found")
}