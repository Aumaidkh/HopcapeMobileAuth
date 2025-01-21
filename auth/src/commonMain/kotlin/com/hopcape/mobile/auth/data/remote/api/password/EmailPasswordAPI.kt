package com.hopcape.mobile.auth.data.remote.api.password

import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.data.remote.dto.login.LoginRequest

interface EmailPasswordAPI {
    suspend fun login(request: LoginRequest): Result<AuthenticatedUser>
}