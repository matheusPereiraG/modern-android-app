package com.android.app.repository

import android.content.SharedPreferences
import com.android.app.domain.LogInDomain
import com.android.app.domain.SignUpDomain
import com.android.app.network.UserAuthService
import com.android.app.network.model.NetworkLogInRequest
import com.android.app.network.model.NetworkLogInResponse
import com.android.app.network.model.NetworkSignUpRequest
import com.android.app.network.model.NetworkSignUpResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class UserAuthRepository @Inject constructor(
    private val userAuthService: UserAuthService,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun signUpUser(
        nome: String?,
        email: String?,
        password: String?,
        administrador: Boolean?
    ): SignUpDomain {
        var signUpResponse: Response<NetworkSignUpResponse>? = null
        try {
            val userRequestBody =
                NetworkSignUpRequest(nome, email, password, administrador.toString())
            signUpResponse = userAuthService.signUpUser(userRequestBody)
        } catch (e: Exception) {
            Timber.w(e)
        }
        return SignUpDomain(signUpResponse?.code(), email)
    }

    suspend fun logInUser(
        email: String,
        password: String
    ): LogInDomain {
        var logInResponse: Response<NetworkLogInResponse>? = null
        try {
            val loginRequestBody =
                NetworkLogInRequest(email, password)
            logInResponse = userAuthService.logInUser(loginRequestBody)
        } catch (e: Exception) {
            Timber.w(e)
        }

        logInResponse?.let { response ->
            if (response.code() == 200) {
                response.body()?.authorization?.let { token ->
                    val editor = sharedPreferences.edit()
                    editor.putString("token", token)
                    editor.commit()
                }
            }
        }

        return LogInDomain(logInResponse?.code(), email)
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