package com.android.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.app.domain.UserDetails

@Entity
data class DatabaseUserDetails constructor(
    @PrimaryKey
    val id: String,
    val nome: String,
    val email: String,
    val password: String,
    val administrador: Boolean
)

fun DatabaseUserDetails.asDomainModel(): UserDetails {
    return UserDetails(
        id = id,
        nome = nome,
        email = email,
        password = password,
        administrador = administrador
    )
}