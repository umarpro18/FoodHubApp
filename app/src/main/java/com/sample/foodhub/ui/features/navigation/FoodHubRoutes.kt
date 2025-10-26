package com.sample.foodhub.ui.features.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class FoodHubRoutes {

    // Auth module route
    @Serializable
    object AuthModuleGraphRoutes : FoodHubRoutes()

    @Serializable
    object AuthScreenRoute : FoodHubRoutes()

    @Serializable
    object SignInScreenRoute : FoodHubRoutes()

    @Serializable
    object SignUpScreenRoute : FoodHubRoutes()

    //Home module route
    @Serializable
    object HomeModuleGraphRoutes : FoodHubRoutes()

    @Serializable
    object HomeRoute: FoodHubRoutes()
}