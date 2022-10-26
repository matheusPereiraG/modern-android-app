package com.android.app.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpDomain(
    val statusCode: Int?,
    val email: String? = ""
): Parcelable