package com.sample.foodhub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sample.foodhub.data.FoodApi
import com.sample.foodhub.ui.features.auth.AuthScreen
import com.sample.foodhub.ui.features.auth.signin.SignInScreen
import com.sample.foodhub.ui.features.auth.signup.SignUpScreen
import com.sample.foodhub.ui.theme.FoodhubAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var foodApi: FoodApi

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        var showSplashScreen = true
        installSplashScreen().apply {
            setKeepOnScreenCondition { showSplashScreen }
            setOnExitAnimationListener { splashScreenView ->
                splashScreenView.remove()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodhubAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppGraph(Modifier.padding(innerPadding))
                    /*Box(modifier = Modifier.padding(innerPadding)) {
                        //AuthScreen() // your login/welcome screen
                        //SignUpScreen()
                    }*/
                }
            }
        }

        if (::foodApi.isInitialized) {
            Log.d("umarNew MainActivity", "FoodApi is initialized")
        }

        lifecycleScope.launch {
            delay(2000)
            showSplashScreen = false
        }
    }
}

@Composable
fun AppGraph(modifier: Modifier) {
    val navHostController: NavHostController = rememberNavController()
    val appStartDestination = FoodHubRoute.AuthModuleGraphRoute

    NavHost(navController = navHostController, startDestination = appStartDestination) {

        navigation<FoodHubRoute.AuthModuleGraphRoute>(startDestination = FoodHubRoute.AuthScreenRoute) {
            composable<FoodHubRoute.AuthScreenRoute> {
                AuthScreen { navHostController.navigate(FoodHubRoute.SignInScreenRoute) }
            }

            composable<FoodHubRoute.SignInScreenRoute> {
                SignInScreen()
            }

            composable<FoodHubRoute.SignUpScreenRoute> {
                SignUpScreen()
            }
        }
    }
}