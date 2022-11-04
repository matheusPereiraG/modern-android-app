package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateProductResponse(
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "_id")
    val id: String? = null,
)