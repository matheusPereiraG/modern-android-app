package com.android.app.network

import com.android.app.network.model.NetworkSignUpRequest
import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthService {

    @POST("/usuarios")
    suspend fun signUpUser(@Body user: NetworkSignUpRequest): NetworkSignUpResponse
}