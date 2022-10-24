package com.android.app.repository

import com.android.app.database.UsersDatabase
import com.android.app.network.UserAuthService
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