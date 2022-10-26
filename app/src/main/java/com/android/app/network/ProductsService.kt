package com.android.app.network

import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsService {

    @POST("/produtos")
    suspend fun signUpUser(
        @Header("Authorization") authToken: String,
        @Query("_id") id: String?,
        @Query("nome") nome: String?,
        @Query("preco") preco: Int,
        @Query("descricao") descricao: String?,
        @Query("quantidade") quantidade: Int
    ): Response<NetworkSignUpResponse>
}