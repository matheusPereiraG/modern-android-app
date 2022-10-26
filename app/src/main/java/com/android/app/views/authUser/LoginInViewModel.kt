package com.android.app.views.authUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.domain.LogInDomain
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginInViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    val logInDomain: MutableLiveData<LogInDomain> by lazy {
        MutableLiveData<LogInDomain>()
    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val logInResponse = userAuthRepository.logInUser(email, password)
            logInDomain.postValue(logInResponse)
        }
    }

}