package com.hopcape.mobile.auth.data.remote.dto.register

import com.hopcape.mobile.auth.api.models.Email

data class RegisterRequest(
    val email: Email,
    val password: String
)
