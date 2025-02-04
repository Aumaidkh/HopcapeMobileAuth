package com.hopcape.mobile.auth.presentation.screens.utils

import androidx.compose.runtime.compositionLocalOf

val LocalViewModelFactory = compositionLocalOf<ViewModelFactory> {
    error("ViewModel factory not found")
}