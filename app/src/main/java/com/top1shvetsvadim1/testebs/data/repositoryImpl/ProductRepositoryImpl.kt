package com.top1shvetsvadim1.testebs.data.repositoryImpl

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.top1shvetsvadim1.testebs.data.database.AppDatabase
import com.top1shvetsvadim1.testebs.data.mapper.ProductItemMapper
import com.top1shvetsvadim1.testebs.data.network.ApiFactory
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.domain.ProductRepository

class ProductRepositoryImpl(application: Application) : ProductRepository {

    private val apiService = ApiFactory.apiService
    private val productItemDao = AppDatabase.getInstance(application).productListDao()
    private val mapper = ProductItemMapper()

    override suspend fun loadProductItemById(id: Int): ProductItem {
        return apiService.getProductItemById(id)
    }

    override suspend fun insertItemToFavorite(productItem: ProductItem) {
        productItemDao.addProductItemFavourite(mapper.mapEntityToDbModel(productItem))
    }

    override suspend fun removeFromFavorite(productItemId: Int) {
        productItemDao.deleteProductItemFromFavorite(productItemId)
    }

    override fun getProductFavoriteList(): LiveData<List<ProductItem>> = Transformations.map(
        productItemDao.getShopListFavorite()
    ){
        mapper.mapListDbModelToListEntity(it)
    }
}