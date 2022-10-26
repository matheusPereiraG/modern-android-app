package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkLogInResponse(
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "authorization")
    val authorization: String? = null
)