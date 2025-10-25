package com.sample.foodhub

import kotlinx.serialization.Serializable

@Serializable
sealed class FoodHubRoute {

    // Auth module route
    @Serializable
    object AuthModuleGraphRoute : FoodHubRoute()

    @Serializable
    object AuthScreenRoute : FoodHubRoute()

    @Serializable
    object SignInScreenRoute : FoodHubRoute()

    @Serializable
    object SignUpScreenRoute : FoodHubRoute()

    //Home module route
}