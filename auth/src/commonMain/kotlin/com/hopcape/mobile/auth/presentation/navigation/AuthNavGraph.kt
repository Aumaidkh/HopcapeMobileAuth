package com.hopcape.mobile.auth.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hopcape.mobile.auth.api.content.LocalContent
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreen
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreenEvent
import com.hopcape.mobile.auth.presentation.screens.login.LoginScreenViewModel
import com.hopcape.mobile.auth.presentation.screens.otp.RequestOtp
import com.hopcape.mobile.auth.presentation.screens.otp.VerifyOtp
import com.hopcape.mobile.auth.presentation.screens.register.RegisterScreen
import com.hopcape.mobile.auth.presentation.screens.register.RegisterScreenEvent
import com.hopcape.mobile.auth.presentation.screens.register.RegisterScreenViewModel
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
            route = NavRoutes.Login.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(300))
            }
        ) {
            val factory = LocalViewModelFactory.current
            val viewModel = remember { factory.create<LoginScreenViewModel>() }

            val state by viewModel.state.collectAsStateWithLifecycle()
            val event by viewModel.event.collectAsStateWithLifecycle(null)

            LaunchedEffect(event) {
                when (event) {
                    is LoginScreenEvent.Navigate -> {
                        navController.navigate((event as LoginScreenEvent.Navigate).route.route)
                    }
                    else -> Unit
                }
            }

            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                onIntent = viewModel::onIntent,
                state = state
            )
        }

        composable(
            route = NavRoutes.Register.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(300))
            }
        ) {
            val screenContent = LocalContent.current.registerScreen
            val factory = LocalViewModelFactory.current
            val viewModel = remember { factory.create<RegisterScreenViewModel>() }

            val state by viewModel.state.collectAsStateWithLifecycle()
            val event by viewModel.event.collectAsStateWithLifecycle(null)

            LaunchedEffect(event) {
                when (event) {
                    is RegisterScreenEvent.NavigateBack -> {
                        navController.popBackStack()
                    }
                    else -> Unit
                }
            }

            RegisterScreen(
                modifier = Modifier.fillMaxSize(),
                content = screenContent,
                onIntent = viewModel::onIntent,
                state = state
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