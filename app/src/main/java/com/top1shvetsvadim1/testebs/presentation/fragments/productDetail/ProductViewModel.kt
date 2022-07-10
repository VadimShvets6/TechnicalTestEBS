package com.top1shvetsvadim1.testebs.presentation.fragments.productDetail

import android.app.Application
import androidx.lifecycle.*
import com.top1shvetsvadim1.testebs.data.repositoryImpl.ProductRepositoryImpl
import com.top1shvetsvadim1.testebs.domain.GetProductItemByIdUseCase
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.presentation.Loading
import com.top1shvetsvadim1.testebs.presentation.State
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = ProductRepositoryImpl(application)
    private val getProductItemByIdUseCase = GetProductItemByIdUseCase(repository)

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _product = MutableLiveData<ProductItem>()
    val product: LiveData<ProductItem>
        get() = _product

    fun getProductById(id : Int){
        val deferred = viewModelScope.async {
            _state.value = Loading(true)
            val result = getProductItemByIdUseCase(id)
            result
        }

        viewModelScope.launch {
            _product.value = deferred.await()
            _state.value = Loading(false)
        }
    }

}