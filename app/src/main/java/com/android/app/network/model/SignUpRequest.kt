package com.android.app.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "nome")
    val name: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "password")
    val password: String? = null,
    @Json(name = "administrador")
    val isAdmin: String? = null
)