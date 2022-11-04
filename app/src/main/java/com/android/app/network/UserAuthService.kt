package com.android.app.network

import com.android.app.network.model.LogInRequest
import com.android.app.network.model.LogInResponse
import com.android.app.network.model.SignUpRequest
import com.android.app.network.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthService {

    @POST("/usuarios")
    suspend fun signUpUser(@Body user: SignUpRequest): Response<SignUpResponse>

    @POST("/login")
    suspend fun logInUser(@Body userCredentials: LogInRequest): Response<LogInResponse>
}