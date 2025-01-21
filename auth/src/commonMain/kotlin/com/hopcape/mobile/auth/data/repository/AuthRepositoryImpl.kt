package com.hopcape.mobile.auth.data.repository

import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.api.session.SessionManager
import com.hopcape.mobile.auth.data.remote.api.password.EmailPasswordAPI
import com.hopcape.mobile.auth.data.remote.dto.login.LoginRequest
import com.hopcape.mobile.auth.domain.model.UserModel

class AuthRepositoryImpl(
    private val emailLoginApi: EmailPasswordAPI,
    private val sessionManager: SessionManager
): AuthRepository {

    override suspend fun login(email: Email, password: Password): Result<UserModel> {
        return emailLoginApi.login(
            LoginRequest(
                email = email.value,
                password = password.value
            )
        ).map {
            sessionManager.storeSession(
                user = it
            )
            UserModel(
                id = it.id
            )
        }
    }

    override suspend fun login(token: IDToken): Result<UserModel> {
        TODO("Not yet implemented")
    }
}