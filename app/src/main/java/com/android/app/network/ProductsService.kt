package com.android.app.network

import com.android.app.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface ProductsService {

    @GET("/produtos")
    suspend fun getProducts(
        @Header("Authorization") authToken: String,
        @Query("_id") id: String?,
        @Query("nome") name: String?,
        @Query("preco") price: Int?,
        @Query("descricao") description: String?,
        @Query("quantidade") quantity: Int?
    ): Response<ProductsResponse>

    @GET("/produtos")
    suspend fun getAllProducts(
        @Header("Authorization") authToken: String,
    ): Response<ProductsResponse>

    @POST("/produtos")
    suspend fun createProduct(
        @Header("Authorization") authToken: String,
        @Body product: CreateProductRequest
    ): Response<CreateProductResponse>
}