package com.hopcape.mobile.auth.data.remote.api.password

import com.hopcape.mobile.auth.api.client.ApiClient
import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.data.remote.api.ApiEndPoint
import com.hopcape.mobile.auth.data.remote.dto.login.LoginRequest

class EmailPasswordAPIImpl(
    private val httpClient: ApiClient,
): EmailPasswordAPI {

    override suspend fun login(request: LoginRequest): Result<AuthenticatedUser> {
        return httpClient.post(
            endPoint = ApiEndPoint.Login.endPoint,
            body = request,
            responseType = AuthenticatedUser::class
        ).map { it.data }
    }
}