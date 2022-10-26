package com.android.app.repository

import android.content.SharedPreferences
import com.android.app.network.ProductsService
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService,
    private val sharedPreferences: SharedPreferences
) {

}