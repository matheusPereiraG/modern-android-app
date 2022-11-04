package com.android.app.repository

import android.content.SharedPreferences
import com.android.app.domain.CreateProductDomain
import com.android.app.network.ProductsService
import com.android.app.network.model.CreateProductRequest
import com.android.app.network.model.CreateProductResponse
import com.android.app.network.model.Product
import com.android.app.network.model.ProductsResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun getProducts(
        id: String?,
        name: String?,
        price: Int?,
        description: String?,
        quantity: Int?
    ): List<Product>? {
        var getProductsResponse: Response<ProductsResponse>? = null
        try {
            val token = sharedPreferences.getString("token", "") ?: ""
            getProductsResponse =
                productsService.getProducts(token, id, name, price, description, quantity)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return getProductsResponse?.body()?.products
    }

    suspend fun getAllProducts(): List<Product>? {
        var getProductsResponse: Response<ProductsResponse>? = null
        try {
            val token = sharedPreferences.getString("token", "") ?: ""
            getProductsResponse =
                productsService.getAllProducts(token)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return getProductsResponse?.body()?.products
    }

    suspend fun createProduct(
        name: String,
        price: Int,
        description: String,
        quantity: Int
    ): CreateProductDomain {
        var createProductsResponse: Response<CreateProductResponse>? = null
        try {
            val token = sharedPreferences.getString("token", "") ?: ""
            createProductsResponse =
                productsService.createProduct(
                    token,
                    CreateProductRequest(name, price, description, quantity)
                )
        } catch (e: Exception) {
            Timber.w(e)
        }

        return CreateProductDomain(
            name,
            createProductsResponse?.body()?.message,
            createProductsResponse?.code()
        )
    }


}