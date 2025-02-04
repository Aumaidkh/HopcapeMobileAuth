package com.hopcape.mobile.auth

import com.hopcape.mobile.auth.presentation.ui.HopcapeMobileAuthApp
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.compose.rememberNavController

fun MainViewController() = ComposeUIViewController {
    val navController = rememberNavController()
    HopcapeMobileAuthApp(
        navController = navController
    )
}