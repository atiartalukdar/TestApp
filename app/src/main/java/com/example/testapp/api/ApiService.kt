package com.example.testapp.api

import com.example.testapp.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    abstract suspend fun getUser(
        @Path("userId") userId : String
    ): UserModel
}