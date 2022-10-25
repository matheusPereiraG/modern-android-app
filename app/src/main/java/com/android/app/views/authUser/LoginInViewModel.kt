package com.android.app.views.authUser

import androidx.lifecycle.ViewModel
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginInViewModel @Inject constructor(
    private val userDetailsRepository: UserAuthRepository
) : ViewModel() {

}