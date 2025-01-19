package com.hopcape.mobile.auth.api.authprovider.strategy

import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.models.AuthenticatedUser

class AppleAuthenticationStrategy: AuthenticationStrategy {
    /**
     * Authenticates the user based on the provided credentials.
     *
     * @param credentials The credentials used to authenticate the user.
     * @return A [Result] containing the authenticated user if successful, or an error if authentication fails.
     */
    override suspend fun authenticate(credentials: AuthCredentials): Result<AuthenticatedUser> {
        TODO("Not yet implemented")
    }

}