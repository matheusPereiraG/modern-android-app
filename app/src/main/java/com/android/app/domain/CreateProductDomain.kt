package com.android.app.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateProductDomain(
    val prodName: String?,
    val message: String?,
    val statusCode: Int?
): Parcelable