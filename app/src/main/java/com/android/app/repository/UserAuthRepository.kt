package com.android.app.repository

import com.android.app.domain.SignUpDomain
import com.android.app.network.UserAuthService
import com.android.app.network.model.NetworkSignUpRequest
import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class UserAuthRepository @Inject constructor(
    private val userAuthService: UserAuthService
) {

    suspend fun signUpUser(
        nome: String?,
        email: String?,
        password: String?,
        administrador: Boolean?
    ): SignUpDomain {
        var userAuthResponse: Response<NetworkSignUpResponse>? = null
        try {
            val userRequestBody =
                NetworkSignUpRequest(nome, email, password, administrador.toString())
            userAuthResponse = userAuthService.signUpUser(userRequestBody)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return SignUpDomain(userAuthResponse?.code(), email)
    }

    suspend fun logInUser(
        email: String,
        password: String
    ) {

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