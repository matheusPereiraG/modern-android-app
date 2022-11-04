package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LogInRequest(
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "password")
    val password: String? = null
)