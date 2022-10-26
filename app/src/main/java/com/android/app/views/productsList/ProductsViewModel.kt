package com.android.app.views.productsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.domain.SignUpDomain
import com.android.app.network.model.Product
import com.android.app.repository.ProductsRepository
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val productsList: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    fun getProducts() =
        viewModelScope.launch(Dispatchers.IO) {
            /*val products = productsRepository.getProducts()
            signUpDomain.postValue(networkResponse)^*/
        }
}