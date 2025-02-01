package com.hopcape.mobile.auth.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.authprovider.methods.AuthMethod
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.di.AuthenticationDependencyModuleSingleton
import kotlinx.coroutines.launch

@Composable
fun HopcapeMobileAuthApp(modifier: Modifier = Modifier) {
    val repository = AuthenticationDependencyModuleSingleton.get<AuthRepository>(AuthRepository::class)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scope = rememberCoroutineScope()
        Text("Welcome")
        Button(
            onClick = {
                scope.launch {
                    repository.login(
                            email = Email("aubc@gmail.com"),
                            password = Password("some Passowrd")
                    ).also {
                        when{
                            it.isSuccess -> {
                                println("API: Success ${it.getOrNull()}")
                            }
                            it.isFailure -> {
                                println("API: Failure ${it.exceptionOrNull()?.message}")
                        }
                    }
                    }
                }
            }
        ){
            Text("Login")
        }
    }
}