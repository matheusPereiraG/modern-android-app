package com.android.app.network.model

import com.android.app.database.DatabaseUserDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSignUpRequest(
    @Json(name = "nome")
    val name: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "password")
    val password: String? = null,
    @Json(name = "administrador")
    val isAdmin: String? = null
)

fun NetworkSignUpRequest.asDatabaseModel(): DatabaseUserDetails {
    return DatabaseUserDetails(
        id = "",
        name ?: "",
        email ?: "",
        password ?: "",
        isAdmin.toBoolean()
    )

}