package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProductsResponse(
    @Json(name = "quantidade")
    val quantity: Int? = null,
    @Json(name = "produtos")
    val products: List<Product>? = null
)

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "_id")
    val id: String? = null,
    @Json(name = "nome")
    val name: String? = null,
    @Json(name = "preco")
    val price: Int? = null,
    @Json(name = "descricao")
    val description: String? = null,
    @Json(name = "quantidade")
    val quantity: Int? = null
)