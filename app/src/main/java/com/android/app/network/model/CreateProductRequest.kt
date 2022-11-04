package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateProductRequest(
    @Json(name = "nome")
    val name: String? = null,
    @Json(name = "preco")
    val preco: Int? = null,
    @Json(name = "descricao")
    val description: String? = null,
    @Json(name = "quantidade")
    val quantity: Int? = null
)