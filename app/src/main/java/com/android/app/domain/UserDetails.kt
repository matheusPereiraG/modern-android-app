package com.android.app.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val id: String?,
    val nome: String? = "",
    val email: String? = "",
    val password: String? = "",
    val administrador: Boolean? = false
) : Parcelable