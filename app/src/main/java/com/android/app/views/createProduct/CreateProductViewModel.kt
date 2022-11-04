package com.android.app.views.createProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.domain.CreateProductDomain
import com.android.app.network.model.Product
import com.android.app.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val createProduct: MutableLiveData<CreateProductDomain> by lazy {
        MutableLiveData<CreateProductDomain>()
    }

    fun createProduct(name: String, price: Int, description: String, quantity: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            val createdProduct = productsRepository.createProduct(name, price, description, quantity)
            createProduct.postValue(createdProduct)
        }
}