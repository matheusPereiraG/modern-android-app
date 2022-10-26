package com.android.app.views.productsList

import androidx.lifecycle.ViewModel
import com.android.app.repository.ProductsRepository
import com.android.app.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

}