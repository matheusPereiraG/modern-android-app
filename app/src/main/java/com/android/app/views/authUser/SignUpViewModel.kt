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

    val currentStatusPair: MutableLiveData<Pair<Int?, String>> by lazy {
        MutableLiveData<Pair<Int?, String>>()
    }

    fun signUpUser(nome: String, email: String, password: String, isAdminCheck: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            val networkResponse =
                userDetailsRepository.signUpUser(nome, email, password, isAdminCheck)
            currentStatusPair.postValue(Pair(networkResponse?.code(), email))
        }
}