package com.hopcape.mobile.auth.presentation.navigation

sealed class NavRoutes(
    val route: String
){
    data object Login: NavRoutes("login")
    data object Register: NavRoutes("register")
    data object VerifyOtp: NavRoutes("verify_otp")
    data object RequestOtp: NavRoutes("request_otp")
}