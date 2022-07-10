package com.top1shvetsvadim1.testebs.presentation.fragments.favouritesScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.top1shvetsvadim1.testebs.data.repositoryImpl.ProductRepositoryImpl
import com.top1shvetsvadim1.testebs.domain.GetProductFavoriteListUseCase
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.domain.RemoveFromFavoritesUseCase
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel(application: Application) : AndroidViewModel(application){

    private val repository = ProductRepositoryImpl(application)
    private val getProductFavoriteListUseCase = GetProductFavoriteListUseCase(repository)
    private val removeFromFavoritesUseCase = RemoveFromFavoritesUseCase(repository)

    val shopList = getProductFavoriteListUseCase()

    fun deleteFromFavorite(productItem: ProductItem){
        if (productItem.isFavorite){
            productItem.isFavorite = false
            viewModelScope.launch {
                removeFromFavoritesUseCase(productItem.id)
            }
        }
    }
}