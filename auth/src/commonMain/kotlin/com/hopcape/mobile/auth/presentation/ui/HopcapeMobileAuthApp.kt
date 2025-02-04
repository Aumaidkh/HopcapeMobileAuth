package com.hopcape.mobile.auth.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hopcape.mobile.auth.api.authprovider.credentials.AuthCredentials
import com.hopcape.mobile.auth.api.authprovider.methods.AuthMethod
import com.hopcape.mobile.auth.api.authprovider.methods.AuthenticationStrategyFactory
import com.hopcape.mobile.auth.api.content.AuthContentProvider
import com.hopcape.mobile.auth.api.models.Email
import com.hopcape.mobile.auth.api.models.Password
import com.hopcape.mobile.auth.data.repository.AuthRepository
import com.hopcape.mobile.auth.di.AuthenticationDependencyModuleSingleton
import com.hopcape.mobile.auth.presentation.navigation.NavRoutes
import com.hopcape.mobile.auth.presentation.navigation.authNavGraph
import kotlinx.coroutines.launch

@Composable
fun HopcapeMobileAuthApp(
    modifier: Modifier = Modifier,
    startDestination: NavRoutes = NavRoutes.Login,
    navController: NavHostController
) {
    AuthContentProvider {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "auth",
        ){
            authNavGraph(
                startDestination = NavRoutes.Login,
                navController = navController
            )
        }
    }
}