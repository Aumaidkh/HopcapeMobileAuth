package com.hopcape.mobile.auth

import com.hopcape.mobile.auth.presentation.ui.HopcapeMobileAuthApp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hopcape.mobile.auth.api.authenticator.Authenticator
import com.hopcape.mobile.auth.api.config.AuthConfigBuilder
import com.hopcape.mobile.auth.api.launcher.AndroidAuthenticationFlowLauncher
import com.hopcape.mobile.auth.di.AndroidPlatformAuthenticationDependencyFactory

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authenticator = Authenticator.create(
            factory = AndroidPlatformAuthenticationDependencyFactory()
        )
        
        authenticator.configure{
            setAuthenticationFlowLauncher(AndroidAuthenticationFlowLauncher(this@MainActivity))
            build()
        }
        
        setContent {
            authenticator.authenticate(
                onAuthenticationSuccess = {
                    Log.d(TAG, "onCreate: Authenticated")
                },
                onAuthenticationFailure = {
                    Log.d(TAG, "onCreate: Authentication Failed")
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}