package com.hopcape.mobile.auth.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreen
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreenViewModel
import com.hopcape.mobile.auth.presentation.screens.otp.RequestOtp
import com.hopcape.mobile.auth.presentation.screens.otp.VerifyOtp
import com.hopcape.mobile.auth.presentation.screens.register.RegisterScreen
import com.hopcape.mobile.auth.presentation.screens.utils.LocalViewModelFactory

fun NavGraphBuilder.authNavGraph(
    startDestination: NavRoutes = NavRoutes.Login,
    navController: NavController
){
    navigation(
        startDestination = startDestination.route,
        route = "auth"
    ){
        composable(
            route = NavRoutes.Login.route
        ){
            val factory = LocalViewModelFactory.current
            val viewModel = remember { factory.create<LoginScreenViewModel>() }

            val state by viewModel.state.collectAsStateWithLifecycle()
            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                onIntent = viewModel::onIntent,
                state = state
            )
        }

        composable(
            route = NavRoutes.Register.route
        ){
            RegisterScreen(
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = NavRoutes.VerifyOtp.route
        ){
            VerifyOtp(
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = NavRoutes.RequestOtp.route
        ){
            RequestOtp(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}