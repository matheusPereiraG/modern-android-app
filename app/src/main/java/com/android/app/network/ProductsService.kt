package com.android.app.network

import com.android.app.network.model.NetworkProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductsService {

    @GET("/produtos")
    suspend fun getProducts(
        @Header("Authorization") authToken: String,
        @Query("_id") id: String?,
        @Query("nome") name: String?,
        @Query("preco") price: Int?,
        @Query("descricao") description: String?,
        @Query("quantidade") quantity: Int?
    ): Response<NetworkProductsResponse>
}