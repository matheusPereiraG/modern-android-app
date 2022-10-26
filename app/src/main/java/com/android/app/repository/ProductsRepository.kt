package com.android.app.repository

import android.content.SharedPreferences
import com.android.app.network.ProductsService
import com.android.app.network.model.NetworkProductsResponse
import com.android.app.network.model.Product
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
        var getProductsResponse: Response<NetworkProductsResponse>? = null
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
        var getProductsResponse: Response<NetworkProductsResponse>? = null
        try {
            val token = sharedPreferences.getString("token", "") ?: ""
            getProductsResponse =
                productsService.getAllProducts(token)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return getProductsResponse?.body()?.products
    }


}