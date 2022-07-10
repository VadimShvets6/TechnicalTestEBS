package com.top1shvetsvadim1.testebs.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductItemDao {

    @Query("SELECT * FROM product_items")
    fun getShopListFavorite() : LiveData<List<ProductItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductItemFavourite(productItemDbModel: ProductItemDbModel)

    @Query("DELETE FROM product_items WHERE id=:productItemId")
    suspend fun deleteProductItemFromFavorite(productItemId : Int)
}