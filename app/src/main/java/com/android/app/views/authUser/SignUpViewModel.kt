package com.android.app.views.authUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userDetailsRepository: UserAuthRepository
) : ViewModel() {

    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun signUpUser(nome: String, email: String, password: String, isAdminCheck: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            val networkResponse =
                userDetailsRepository.signUpUser(nome, email, password, isAdminCheck)

            when (networkResponse?.code()) {
                201 -> message.postValue("Happy days! An account was created for $email")
                400 -> message.postValue("Sorry but an account already exists for $email")
                else -> {
                    message.postValue("Something is wrong! Stay calm, our engineers are looking into it.")
                }
            }
        }
}