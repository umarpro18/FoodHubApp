package com.sample.foodhub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
import com.sample.foodhub.ui.features.auth.signup.SignUpViewModel
import com.sample.foodhub.ui.features.navigation.FoodHubRoutes
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
        //enableEdgeToEdge()
        setContent {
            FoodhubAndroidTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    AppGraph(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        if (::foodApi.isInitialized) {
            Log.d("umarNew MainActivity", "FoodApi is initialized")
        }

        lifecycleScope.launch {
            delay(1500)
            showSplashScreen = false
        }
    }
}

@Composable
fun AppGraph(modifier: Modifier) {
    val navHostController: NavHostController = rememberNavController()
    val appStartDestination = FoodHubRoutes.AuthModuleGraphRoutes

    NavHost(navController = navHostController, startDestination = appStartDestination) {
        navigation<FoodHubRoutes.AuthModuleGraphRoutes>(startDestination = FoodHubRoutes.AuthScreenRoute) {
            composable<FoodHubRoutes.AuthScreenRoute> {
                AuthScreen(
                    onSignUpClicked = { navHostController.navigate(FoodHubRoutes.SignUpScreenRoute) },
                    onSignInClicked = { navHostController.navigate(FoodHubRoutes.SignInScreenRoute) })
            }

            composable<FoodHubRoutes.SignInScreenRoute> {
                SignInScreen()
            }

            composable<FoodHubRoutes.SignUpScreenRoute> {
                val viewModel: SignUpViewModel = hiltViewModel<SignUpViewModel>()
                SignUpScreen(
                    viewModel,
                    onSignUpSuccess = {
                        navHostController.navigate(FoodHubRoutes.HomeModuleGraphRoutes) {
                            popUpTo(FoodHubRoutes.AuthModuleGraphRoutes) {
                                inclusive = true
                            }
                        }
                    })
            }
        }

        navigation<FoodHubRoutes.HomeModuleGraphRoutes>(startDestination = FoodHubRoutes.HomeRoute) {
            composable<FoodHubRoutes.HomeRoute> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Magenta)
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Welcome to Home",
                        fontSize = 40.sp,
                        modifier = Modifier.align(alignment = Alignment.CenterStart)
                    )
                }
            }
        }
    }
}