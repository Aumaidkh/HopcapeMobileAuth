package com.hopcape.mobile.auth.data.remote.strategies.email

import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.authprovider.strategy.AuthenticationStrategy
import com.hopcape.mobile.auth.api.models.AuthenticatedUser
import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.ID
import com.hopcape.mobile.auth.api.models.IDToken
import com.hopcape.mobile.auth.data.remote.strategies.email.register.RegisterBody
import com.hopcape.networking.api.Url
import com.hopcape.networking.api.client.NetworkingClient
import com.hopcape.networking.api.request.methods.HttpMethod

internal class EmailPasswordRegistrationStrategy(
    private val client: NetworkingClient = NetworkingClient.getInstance()
) : AuthenticationStrategy {


    private val endPointUrl by lazy {
       Url("http://10.0.2.2:3000/v1/register")// Authenticator.config.endPoints.registerEndpoint
    }


    override suspend fun authenticate(credentials: AuthCredentials): Result<AuthenticatedUser> {
        return client.makeRequest(
            responseType = LoginResponse::class,
            requestBuilder = {
                setUrl(endPointUrl)
                setMethod(HttpMethod.POST)
                setBody(RegisterBody(credentials as AuthCredentials.EmailPasswordRegistration).toString())
                build()
            }
        ).map {
            AuthenticatedUser(
                id = ID(it.id),
                email = Email(it.email),
                idToken = IDToken(it.idToken),
                username = it.username
            )
        }
    }
}