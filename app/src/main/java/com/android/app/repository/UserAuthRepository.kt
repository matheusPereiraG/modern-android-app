package com.android.app.repository

import com.android.app.database.UsersDatabase
import com.android.app.domain.UserDetails
import com.android.app.network.UserAuthService
import com.android.app.network.model.NetworkSignUpRequest
import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class UserAuthRepository @Inject constructor(
    private val userAuthService: UserAuthService,
    private val database: UsersDatabase
) {

    suspend fun signUpUser(
        nome: String?,
        email: String?,
        password: String?,
        administrador: Boolean?
    ): Response<NetworkSignUpResponse>? {
        var userAuthResponse : Response<NetworkSignUpResponse>? = null
        try {
            val userRequestBody = NetworkSignUpRequest(nome, email, password, administrador.toString())
            userAuthResponse = userAuthService.signUpUser(userRequestBody)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return userAuthResponse
    }

    /*fun getUserDetails(user: String): LiveData<UserDetails> {
        return Transformations.map(database.usersDao.getUserDetails(user)) {
            it?.asDomainModel()
        }
    }


    suspend fun refreshUserDetails(user: String) {
        try {
            val userDetails = userDetailsService.getUserDetails(user)
            database.usersDao.insertUserDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }*/

}