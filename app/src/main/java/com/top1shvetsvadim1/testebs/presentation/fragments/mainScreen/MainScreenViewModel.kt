package com.top1shvetsvadim1.testebs.presentation.fragments.mainScreen

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.top1shvetsvadim1.testebs.data.pagingSource.ProductPagingSource
import com.top1shvetsvadim1.testebs.data.network.ApiFactory
import com.top1shvetsvadim1.testebs.data.repositoryImpl.ProductRepositoryImpl
import com.top1shvetsvadim1.testebs.domain.InsertItemToFavouriteUseCase
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.domain.RemoveFromFavoritesUseCase
import com.top1shvetsvadim1.testebs.presentation.State
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = ApiFactory.apiService

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val repository = ProductRepositoryImpl(application)
    private val insertItemToFavouriteUseCase = InsertItemToFavouriteUseCase(repository)
    private val removeFromFavoritesUseCase = RemoveFromFavoritesUseCase(repository)

    val listData = Pager(PagingConfig(pageSize = 1)) {
        ProductPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

    fun deleteFromFavorite(productItem: ProductItem) {
        if (productItem.isFavorite) {
            productItem.isFavorite = false
            viewModelScope.launch {
                removeFromFavoritesUseCase(productItem.id)
            }
        }
    }

    fun addToFavorite(productItem: ProductItem) {
        if (!productItem.isFavorite) {
            productItem.isFavorite = true
            viewModelScope.launch {
                insertItemToFavouriteUseCase(productItem)
            }
        }
    }
}