package com.android.app.views.authUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.domain.SignUpDomain
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userDetailsRepository: UserAuthRepository
) : ViewModel() {

    val signUpDomain: MutableLiveData<SignUpDomain> by lazy {
        MutableLiveData<SignUpDomain>()
    }

    fun signUpUser(nome: String, email: String, password: String, isAdminCheck: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            val networkResponse =
                userDetailsRepository.signUpUser(nome, email, password, isAdminCheck)
            signUpDomain.postValue(networkResponse)
        }
}