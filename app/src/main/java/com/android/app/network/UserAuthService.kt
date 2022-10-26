package com.android.app.network

import com.android.app.network.model.NetworkLogInRequest
import com.android.app.network.model.NetworkLogInResponse
import com.android.app.network.model.NetworkSignUpRequest
import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAuthService {

    @POST("/usuarios")
    suspend fun signUpUser(@Body user: NetworkSignUpRequest): Response<NetworkSignUpResponse>

    @POST("/login")
    suspend fun logInUser(@Body userCredentials: NetworkLogInRequest): Response<NetworkLogInResponse>
}