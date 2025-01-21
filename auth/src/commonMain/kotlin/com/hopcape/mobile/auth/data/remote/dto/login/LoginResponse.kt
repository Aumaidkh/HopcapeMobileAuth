package com.hopcape.mobile.auth.data.remote.dto.login

data class LoginResponse(
    val token: String,
    val email: String,
    val id: String
)
