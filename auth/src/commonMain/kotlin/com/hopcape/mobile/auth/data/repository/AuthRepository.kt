package com.hopcape.mobile.auth.data.repository

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.domain.model.UserModel

interface AuthRepository {
    suspend fun login(email: Email,password: Password): Result<UserModel>
    suspend fun login(token: IDToken): Result<UserModel>
}