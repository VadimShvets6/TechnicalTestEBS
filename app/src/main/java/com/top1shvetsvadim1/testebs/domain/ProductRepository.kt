package com.top1shvetsvadim1.testebs.domain

import androidx.lifecycle.LiveData

interface ProductRepository {

    suspend fun loadProductItemById(id : Int) : ProductItem

    suspend fun insertItemToFavorite(productItem: ProductItem)

    suspend fun removeFromFavorite(productItemId: Int)

    fun getProductFavoriteList() : LiveData<List<ProductItem>>
}