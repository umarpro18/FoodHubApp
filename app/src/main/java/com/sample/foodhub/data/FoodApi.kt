package com.sample.foodhub.data

import com.sample.foodhub.data.models.AuthResponse
import com.sample.foodhub.data.models.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface FoodApi {

    @GET("food")
    suspend fun getFood(): List<String>


    @POST("/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

}